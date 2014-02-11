package info.movito.themoviedbapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public abstract class AbstractApiElement {

    public static final String PARAM_YEAR = "year";
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_LANGUAGE = "language";
    public static final String PARAM_ID = "id";
    public static final String PARAM_ADULT = "include_adult";
    public static final String PARAM_API_KEY = "api_key";


    protected static final ObjectMapper jsonMapper = new ObjectMapper();

    protected final TmdbApi tmdbApi;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    AbstractApiElement(TmdbApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }


    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> someClass) {
        return mapJsonResult(apiUrl, someClass, null);
    }


    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> someClass, String jsonBody) {
        return mapJsonResult(apiUrl, someClass, jsonBody, RequestMethod.GET);
    }


    public <T> T mapJsonResult(ApiUrl apiUrl, Class<T> someClass, String jsonBody, RequestMethod requestMethod) {
        String webpage = tmdbApi.requestWebPage(apiUrl, jsonBody, requestMethod);

        try {
            return jsonMapper.readValue(webpage, someClass);
        } catch (IOException ex) {
            logger.warn("Failed to get company movies: {}", ex.getMessage());
            throw new MovieDbException(MovieDbException.MovieDbExceptionType.MAPPING_FAILED, webpage, ex);
        }
    }
}
