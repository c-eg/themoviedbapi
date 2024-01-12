package info.movito.themoviedbapi;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public static final String PARAM_ID = "id";

    public static final String PARAM_ADULT = "include_adult";

    public static final String PARAM_API_KEY = "api_key";

    @Getter(AccessLevel.PROTECTED)
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ObjectReader responseStatusReader = objectMapper.readerFor(ResponseStatus.class);

    protected final TmdbApi tmdbApi;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    AbstractTmdbApi(TmdbApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl the api url to map
     * @param clazz the class to map to
     * @param <T> the type of class to map to
     * @return the mapped class
     */
    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> clazz) throws TmdbException {
        return mapJsonResult(apiUrl, null, clazz);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl the api url to map
     * @param jsonBody the json body
     * @param clazz the class to map to
     * @param <T> the type of class to map to
     * @return the mapped class.
     */
    public <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, Class<T> clazz) throws TmdbException {
        return mapJsonResult(apiUrl, jsonBody, RequestType.GET, clazz);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl the api url to map
     * @param jsonBody the json body
     * @param requestType the type of request
     * @param clazz the class to map to
     * @param <T> the type of class to map to
     * @return the mapped class.
     */
    public <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, RequestType requestType, Class<T> clazz) throws TmdbException {
        String jsonResponse = tmdbApi.getTmdbUrlReader().readUrl(apiUrl.buildUrl(), jsonBody, requestType);

        try {
            // check if the response was successful. tmdb have their own codes for successful and unsuccessful responses.
            // some 2xx codes are not successful. See: https://developer.themoviedb.org/docs/errors for more info.
            ResponseStatus responseStatus = responseStatusReader.readValue(jsonResponse);
            Integer statusCode = responseStatus.getStatusCode();

            if (statusCode != null) {
                TmdbResponseCode tmdbResponseCode = TmdbResponseCode.fromCode(statusCode);

                if (tmdbResponseCode != null) {
                    if (REQUEST_LIMIT_EXCEEDED == tmdbResponseCode) {
                        Thread.sleep(1000);
                        return mapJsonResult(apiUrl, jsonBody, requestType, clazz);
                    }
                    else if (!tmdbResponseCode.isSuccess()) {
                        throw new TmdbResponseException(tmdbResponseCode);
                    }
                }
            }

            return objectMapper.readValue(jsonResponse, clazz);
        }
        catch (JsonProcessingException | InterruptedException exception) {
            throw new TmdbException(exception);
        }
    }
}
