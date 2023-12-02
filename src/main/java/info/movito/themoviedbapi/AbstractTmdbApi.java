package info.movito.themoviedbapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.movito.themoviedbapi.model.core.ResponseStatus;
import info.movito.themoviedbapi.model.core.ResponseStatusException;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

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

    protected static final ObjectMapper jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // see https://www.themoviedb.org/documentation/api/status-codes
    private static final Collection<Integer> SUCCESS_STATUS_CODES = Arrays.asList(
        1, // Success
        12, // The item/record was updated successfully.
        13 // The item/record was updated successfully.
    );

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
    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> clazz) {
        return mapJsonResult(apiUrl, clazz, null);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl the api url to map
     * @param clazz the class to map to
     * @param jsonBody the json body
     * @param <T> the type of class to map to
     * @return the mapped class.
     */
    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> clazz, String jsonBody) {
        return mapJsonResult(apiUrl, clazz, jsonBody, RequestType.GET);
    }

    /**
     * Maps a json result to a class.
     *
     * @param apiUrl the api url to map
     * @param clazz the class to map to
     * @param jsonBody the json body
     * @param requestType the type of request
     * @param <T> the type of class to map to
     * @return the mapped class.
     */
    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> clazz, String jsonBody, RequestType requestType) {
        String webpage = tmdbApi.requestWebPage(apiUrl, jsonBody, requestType);

        try {
            // check if was error responseStatus
            ResponseStatus responseStatus = jsonMapper.readValue(webpage, ResponseStatus.class);
            // work around the problem that there's no status code for suspected spam names yet
            String suspectedSpam = "Unable to create list because: Description is suspected to be spam.";
            if (webpage.contains(suspectedSpam)) {
                responseStatus = new ResponseStatus(-100, suspectedSpam);
            }

            // if null, the json response was not a error responseStatus code, but something else
            Integer statusCode = responseStatus.getStatusCode();
            if (statusCode != null && !SUCCESS_STATUS_CODES.contains(statusCode)) {
                throw new ResponseStatusException(responseStatus);
            }

            return jsonMapper.readValue(webpage, clazz);
        }
        catch (IOException ex) {
            throw new MovieDbException("mapping failed:\n" + webpage, ex);
        }
    }
}
