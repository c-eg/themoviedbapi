package info.movito.themoviedbapi.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import static info.movito.themoviedbapi.tools.TmdbResponseCode.REQUEST_LIMIT_EXCEEDED;

/**
 * Executes requests against the TMDB API and maps the json responses to model classes. Shared by the {@code Tmdb*} api classes.
 */
@Slf4j
public class TmdbApiClient {
    private static final ObjectReader RESPONSE_STATUS_READER = JsonUtil.OBJECT_MAPPER.readerFor(ResponseStatus.class);

    private static final int MAX_RATE_LIMIT_RETRIES = 3;

    private static final long RATE_LIMIT_RETRY_DELAY_MS = 1000;

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
        return request(apiUrl, null, RequestType.GET, JsonUtil.OBJECT_MAPPER.readerFor(clazz), 0);
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
        return request(apiUrl, null, RequestType.GET, JsonUtil.OBJECT_MAPPER.readerFor(resultType), 0);
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
        return request(apiUrl, jsonBody, requestType, JsonUtil.OBJECT_MAPPER.readerFor(clazz), 0);
    }

    private <T> T request(ApiUrl apiUrl, String jsonBody, RequestType requestType, ObjectReader objectReader, int attempt)
        throws TmdbException {
        TmdbResponse response = requestExecutor.execute(new TmdbRequest(apiUrl.buildUrl(), requestType, jsonBody));
        String jsonResponse = response.body();

        // TMDB has their own codes for successful and unsuccessful responses. Some 2xx HTTP responses are not successful and
        // carry the real outcome in the body. See: https://developer.themoviedb.org/docs/errors for more info.
        TmdbResponseCode tmdbResponseCode = parseResponseCode(jsonResponse);

        // TMDB signals rate limiting both via HTTP 429 and via their own status code, regardless of the body format.
        boolean rateLimited = response.statusCode() == REQUEST_LIMIT_EXCEEDED.getHttpStatus() || REQUEST_LIMIT_EXCEEDED == tmdbResponseCode;
        if (rateLimited) {
            if (attempt >= MAX_RATE_LIMIT_RETRIES) {
                throw new TmdbResponseException(REQUEST_LIMIT_EXCEEDED);
            }
            log.info("TMDB API: Request limit exceeded. Waiting {}ms before retry {} of {}.",
                RATE_LIMIT_RETRY_DELAY_MS, attempt + 1, MAX_RATE_LIMIT_RETRIES);
            sleep();
            return request(apiUrl, jsonBody, requestType, objectReader, attempt + 1);
        }

        if (tmdbResponseCode != null && !tmdbResponseCode.isSuccess()) {
            throw new TmdbResponseException(tmdbResponseCode);
        }

        // An HTTP error with no recognisable TMDB status code (e.g. an HTML gateway error) cannot be mapped to the result type,
        // so surface the status instead of failing later with an opaque json parsing error.
        if (tmdbResponseCode == null && !response.isSuccessfulHttpStatus()) {
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
    private static TmdbResponseCode parseResponseCode(String jsonResponse) {
        try {
            ResponseStatus responseStatus = RESPONSE_STATUS_READER.readValue(jsonResponse);
            return responseStatus.getStatusCode();
        }
        catch (JsonProcessingException exception) {
            // not an error - a successful response body is usually not a TMDB status object
            return null;
        }
    }

    private static void sleep() throws TmdbException {
        try {
            Thread.sleep(RATE_LIMIT_RETRY_DELAY_MS);
        }
        catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new TmdbException(exception);
        }
    }
}
