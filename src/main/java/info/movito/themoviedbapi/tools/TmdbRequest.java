package info.movito.themoviedbapi.tools;

import java.util.Objects;

/**
 * A request to be made against the TMDB API.
 *
 * @param url         the url to make the request to
 * @param requestType the type of request to make
 * @param jsonBody    the json body to send with the request, only used for {@link RequestType#POST} requests, may be {@code null}
 */
public record TmdbRequest(String url, RequestType requestType, String jsonBody) {
    /**
     * Validates that the required components of the request are present.
     */
    public TmdbRequest {
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(requestType, "requestType must not be null");
    }

    /**
     * Creates a request without a body.
     *
     * @param url         the url to make the request to
     * @param requestType the type of request to make
     */
    public TmdbRequest(String url, RequestType requestType) {
        this(url, requestType, null);
    }
}
