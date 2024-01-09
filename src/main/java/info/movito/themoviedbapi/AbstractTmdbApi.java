package info.movito.themoviedbapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import lombok.AccessLevel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> clazz) throws TmdbResponseException {
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
    public <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, Class<T> clazz) throws TmdbResponseException {
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
    public <T> T mapJsonResult(ApiUrl apiUrl, String jsonBody, RequestType requestType, Class<T> clazz) throws TmdbResponseException {
        String jsonResponse = tmdbApi.getTmdbUrlReader().readUrl(apiUrl.buildUrl(), jsonBody, requestType);

        try {
            return objectMapper.readValue(jsonResponse, clazz);
        }
        catch (IOException exception) {
            throw new TmdbResponseException(exception.getMessage());
        }
    }
}
