package info.movito.themoviedbapi.tools;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.util.JsonUtil;

/**
 * Executes requests against the TMDB API and maps the json responses to model classes. Shared by the {@code Tmdb*} api classes.
 */
public class TmdbApiClient {
    private static final ObjectReader RESPONSE_STATUS_READER = JsonUtil.OBJECT_MAPPER.readerFor(ResponseStatus.class);

    private final TmdbRequestExecutor requestExecutor;

    /**
     * Constructor.
     *
     * @param requestExecutor the request executor to use to make requests to tmdb.
     */
    public TmdbApiClient(TmdbRequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    /**
     * Executes a GET request and maps the json response to the given class.
     *
     * @param apiUrl the api url to request
     * @param clazz  the class to map the response to
     * @param <T>    the type of class to map to
     * @return the mapped class
     */
    public <T> T get(ApiUrl apiUrl, Class<T> clazz) throws TmdbException {
        return request(apiUrl, null, RequestType.GET, JsonUtil.OBJECT_MAPPER.readerFor(clazz));
    }

    /**
     * Executes a GET request and maps the json response to the given generic type.
     *
     * @param apiUrl     the api url to request
     * @param resultType the type to map the response to
     * @param <T>        the type to map to
     * @return the mapped result
     */
    public <T> T get(ApiUrl apiUrl, TypeReference<T> resultType) throws TmdbException {
        return request(apiUrl, null, RequestType.GET, JsonUtil.OBJECT_MAPPER.readerFor(resultType));
    }

    /**
     * Executes a request and maps the json response to the given class.
     *
     * @param apiUrl      the api url to request
     * @param jsonBody    the json body to send, may be {@code null}
     * @param requestType the type of request
     * @param clazz       the class to map the response to
     * @param <T>         the type of class to map to
     * @return the mapped class
     */
    public <T> T request(ApiUrl apiUrl, String jsonBody, RequestType requestType, Class<T> clazz) throws TmdbException {
        return request(apiUrl, jsonBody, requestType, JsonUtil.OBJECT_MAPPER.readerFor(clazz));
    }

    private <T> T request(ApiUrl apiUrl, String jsonBody, RequestType requestType, ObjectReader objectReader) throws TmdbException {
        TmdbResponse response = requestExecutor.execute(new TmdbRequest(apiUrl.buildUrl(), requestType, jsonBody));
        String jsonResponse = response.body();

        if (!response.isSuccessfulHttpStatus()) {
            Optional<TmdbResponseCode> tmdbResponseCode = parseResponseCode(jsonResponse);
            if (tmdbResponseCode.isPresent()) {
                throw new TmdbResponseException(tmdbResponseCode.get());
            }

            // An HTTP error with no recognisable TMDB status code (e.g. an HTML gateway error) cannot be mapped to the result type,
            throw new TmdbResponseException("TMDB API returned HTTP " + response.statusCode() + ": " + jsonResponse);
        }

        try {
            return objectReader.readValue(jsonResponse);
        }
        catch (JsonProcessingException exception) {
            throw new TmdbException(exception);
        }
    }

    /**
     * Parses the TMDB status code from a response body, returning {@code null} if the body is not a TMDB status object.
     */
    private static Optional<TmdbResponseCode> parseResponseCode(String jsonResponse) {
        try {
            ResponseStatus responseStatus = RESPONSE_STATUS_READER.readValue(jsonResponse);
            return Optional.of(responseStatus.getStatusCode());
        }
        catch (JsonProcessingException exception) {
            // not an error - a successful response body is usually not a TMDB status object
            return Optional.empty();
        }
    }
}
