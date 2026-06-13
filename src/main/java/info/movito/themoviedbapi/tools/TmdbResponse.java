package info.movito.themoviedbapi.tools;

/**
 * A response returned from the TMDB API.
 *
 * @param statusCode the HTTP status code of the response
 * @param body       the raw response body
 */
public record TmdbResponse(int statusCode, String body) {
    /**
     * Returns whether the HTTP status code indicates success (2xx).
     */
    public boolean isSuccessfulHttpStatus() {
        return statusCode >= 200 && statusCode < 300;
    }
}
