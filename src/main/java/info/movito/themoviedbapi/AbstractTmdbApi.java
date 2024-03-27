package info.movito.themoviedbapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static info.movito.themoviedbapi.tools.TmdbResponseCode.REQUEST_LIMIT_EXCEEDED;

/**
 * Class to be inherited by a TmdbApi class.
 */
public abstract class AbstractTmdbApi {
    public static final String PARAM_YEAR = "year";

    public static final String PARAM_PAGE = "page";

    public static final String PARAM_LANGUAGE = "language";

    public static final String PARAM_ADULT = "include_adult";

    public static final String PARAM_SORT_BY = "sort_by";

    @Getter(AccessLevel.PROTECTED)
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ObjectReader responseStatusReader = objectMapper.readerFor(ResponseStatus.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTmdbApi.class);

    private final TmdbApi tmdbApi;

    AbstractTmdbApi(TmdbApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl the api url to map
     * @param clazz  the class to map to
     * @param <T>    the type of class to map to
     * @return the mapped class
     */
    protected <T> T mapJsonResult(ApiUrl apiUrl, Class<T> clazz) throws TmdbException {
        return mapJsonResult(apiUrl, null, clazz);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl      the api url to map
     * @param resultClass the class to map to
     * @param <T>         the type of class to map to
     * @return the mapped class
     */
    protected <T> T mapJsonResult(ApiUrl apiUrl, TypeReference<T> resultClass) throws TmdbException {
        return mapJsonResult(apiUrl, null, resultClass);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl   the api url to map
     * @param jsonBody the json body
     * @param clazz    the class to map to
     * @param <T>      the type of class to map to
     * @return the mapped class.
     */
    protected <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, Class<T> clazz) throws TmdbException {
        return mapJsonResult(apiUrl, jsonBody, RequestType.GET, clazz);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl      the api url to map
     * @param jsonBody    the json body
     * @param resultClass the class to map to
     * @param <T>         the type of class to map to
     * @return the mapped class
     */
    protected <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, TypeReference<T> resultClass) throws TmdbException {
        return mapJsonResult(apiUrl, jsonBody, RequestType.GET, resultClass);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl      the api url to map
     * @param jsonBody    the json body
     * @param requestType the type of request
     * @param clazz       the class to map to
     * @param <T>         the type of class to map to
     * @return the mapped class.
     */
    protected <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, RequestType requestType, Class<T> clazz) throws TmdbException {
        return mapJsonResult(apiUrl, jsonBody, requestType, objectMapper.readerFor(clazz));
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl      the api url to map
     * @param jsonBody    the json body
     * @param requestType the type of request
     * @param resultClass the class to map to
     * @param <T>         the type of class to map to
     * @return the mapped class.
     */
    protected <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, RequestType requestType, TypeReference<T> resultClass)
        throws TmdbException {
        return mapJsonResult(apiUrl, jsonBody, requestType, objectMapper.readerFor(resultClass));
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl       the api url to map
     * @param jsonBody     the json body
     * @param requestType  the type of request
     * @param objectReader the object reader
     * @param <T>          the type of class to map to
     * @return the mapped class.
     */
    private <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, RequestType requestType, ObjectReader objectReader) throws TmdbException {
        String jsonResponse = tmdbApi.getTmdbUrlReader().readUrl(apiUrl.buildUrl(), jsonBody, requestType);

        try {
            // check if the response was successful. tmdb have their own codes for successful and unsuccessful responses.
            // some 2xx codes are not successful. See: https://developer.themoviedb.org/docs/errors for more info.
            ResponseStatus responseStatus = responseStatusReader.readValue(jsonResponse);
            TmdbResponseCode tmdbResponseCode = responseStatus.getStatusCode();

            if (tmdbResponseCode != null) {
                if (REQUEST_LIMIT_EXCEEDED == tmdbResponseCode) {
                    LOGGER.info("TMDB API: Request limit exceeded. Waiting 1 second before retrying.");
                    Thread.sleep(1000);
                    return mapJsonResult(apiUrl, jsonBody, requestType, objectReader);
                }
                else if (!tmdbResponseCode.isSuccess()) {
                    throw new TmdbResponseException(tmdbResponseCode);
                }
            }
        }
        catch (JsonProcessingException exception) {
            // ignore, not an error - caused by responseStatusReader.readValue(jsonResponse);
            // this is necessary because if some requests fail (including 2xx responses), the response is a json object
        }
        catch (InterruptedException exception) {
            throw new TmdbException(exception);
        }

        try {
            return objectReader.readValue(jsonResponse);
        }
        catch (JsonProcessingException exception) {
            throw new TmdbException(exception);
        }
    }
}
