package uk.co.conoregan.themoviedbapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.model.core.responses.TmdbResponseException;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponseCode;

import java.io.IOException;

import static uk.co.conoregan.themoviedbapi.tools.TmdbResponseCode.REQUEST_LIMIT_EXCEEDED;

/**
 * Class to be inherited by a TmdbApi class.
 */
public abstract class AbstractTmdbApi {
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final OkHttpClient okHttpClient = new OkHttpClient();

    protected final TmdbApi tmdbApi;

    public static final String PARAM_YEAR = "year";

    public static final String PARAM_PAGE = "page";

    public static final String PARAM_LANGUAGE = "language";

    public static final String PARAM_ADULT = "include_adult";

    public static final String PARAM_SORT_BY = "sort_by";

    AbstractTmdbApi(TmdbApi tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    /**
     * Maps the json result to the specified class.
     *
     * @param json the json to map
     * @param resultClass the class to map to
     * @param <T> the type of the result
     * @return the mapped result
     * @throws TmdbException if the json could not be mapped
     */
    protected <T> T mapJsonResult(String json, Class<T> resultClass) throws TmdbException {
        try {
            return objectMapper.readValue(json, resultClass);
        }
        catch (JsonProcessingException exception) {
            throw new TmdbException(exception.getMessage());
        }
    }

    /**
     * Makes a GET request to the TMDb API.
     *
     * @param apiEndpoint the api endpoint (e.g. "movies/list")
     * @return the string body, response
     * @throws TmdbResponseException if the response was not successful
     */
    protected String makeGetRequest(ApiEndpoint apiEndpoint) throws TmdbResponseException {
        return makeRequest(apiEndpoint, null, RequestType.GET);
    }

    /**
     * Makes a POST request to the TMDb API.
     *
     * @param apiEndpoint the api endpoint (e.g. "movies/list")
     * @param json the json body
     * @return the string body, response
     * @throws TmdbResponseException if the response was not successful
     */
    protected String makePostRequest(ApiEndpoint apiEndpoint, String json) throws TmdbResponseException {
        return makeRequest(apiEndpoint, json, RequestType.POST);
    }

    /**
     * Makes a DELETE request to the TMDb API.
     *
     * @param apiEndpoint the api endpoint (e.g. "movies/list")
     * @param json the json body
     * @return the string body, response
     * @throws TmdbResponseException if the response was not successful
     */
    protected String makeDeleteRequest(ApiEndpoint apiEndpoint, String json) throws TmdbResponseException {
        return makeRequest(apiEndpoint, json, RequestType.DELETE);
    }

    /**
     * Makes a DELETE request to the TMDb API.
     *
     * @param apiEndpoint the api endpoint (e.g. "movies/list")
     * @return the string body, response
     * @throws TmdbResponseException if the response was not successful
     */
    protected String makeDeleteRequest(ApiEndpoint apiEndpoint) throws TmdbResponseException {
        return makeRequest(apiEndpoint, null, RequestType.DELETE);
    }

    /**
     * Makes a request to the TMDb API.
     *
     * @param apiEndpoint the api endpoint (e.g. "movies/list")
     * @param json the json body
     * @param requestType the type of request
     * @return the string body, response
     * @throws TmdbResponseException if the response was not successful
     */
    protected String makeRequest(ApiEndpoint apiEndpoint, String json, RequestType requestType) throws TmdbResponseException {
        return makeRequest(tmdbApi.getTmdbBaseUrl(), apiEndpoint, json, requestType);
    }

    /**
     * Makes a request to the TMDb API.
     *
     * @param baseUrl the base url (e.g. "https://api.themoviedb.org/3/")
     * @param apiEndpoint the api endpoint (e.g. "movies/list")
     * @param json the json body
     * @param requestType the type of request
     * @return the string body, response
     * @throws TmdbResponseException if the response was not successful
     */
    protected String makeRequest(String baseUrl, ApiEndpoint apiEndpoint, String json, RequestType requestType)
        throws TmdbResponseException {
        Request.Builder requestBuilder = new Request.Builder()
            .url(baseUrl + apiEndpoint.buildUrl())
            .addHeader("Authorization", "Bearer " + tmdbApi.getApiKey())
            .addHeader("Accept", "application/json")
            .addHeader("Content-type", "application/json");

        switch (requestType) {
            case GET -> requestBuilder.get();
            case POST -> requestBuilder.post(okhttp3.RequestBody.create(json, okhttp3.MediaType.parse("application/json")));
            case DELETE -> requestBuilder.delete();
            default -> throw new RuntimeException("Invalid request type: " + requestType);
        }

        Request request = requestBuilder.build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new TmdbResponseException("Response body was null: " + response);
            }
            String responseString = responseBody.string();

            // check if the response was successful. tmdb have their own codes for successful and unsuccessful responses.
            // some 2xx codes are not successful. See: https://developer.themoviedb.org/docs/errors for more info.
            ResponseStatus responseStatus = objectMapper.readValue(responseString, ResponseStatus.class);
            Integer statusCode = responseStatus.getStatusCode();
            if (statusCode != null) {
                TmdbResponseCode tmdbResponseCode = TmdbResponseCode.fromCode(statusCode);

                if (tmdbResponseCode != null) {
                    if (REQUEST_LIMIT_EXCEEDED == tmdbResponseCode) {
                        Thread.sleep(1000);
                        return makeRequest(apiEndpoint, json, requestType);
                    }
                    else if (!tmdbResponseCode.isSuccess()) {
                        throw new TmdbResponseException(tmdbResponseCode);
                    }
                }
            }

            return responseString;
        }
        catch (IOException | InterruptedException exception) {
            throw new TmdbResponseException("An unexpected error occurred: " + exception);
        }
    }
}
