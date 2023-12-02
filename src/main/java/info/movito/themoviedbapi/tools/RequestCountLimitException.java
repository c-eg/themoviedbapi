package info.movito.themoviedbapi.tools;

/**
 * Exception that is thrown if the request limit is reached.
 */
public class RequestCountLimitException extends RuntimeException {
    /**
     * Retry internal in seconds.
     */
    private final int retryAfter;

    /**
     * @param jsonResposne The json response from the server.
     * @param retryAfter   The retry interval in seconds.
     */
    public RequestCountLimitException(String jsonResposne, int retryAfter) {
        super(jsonResposne);
        this.retryAfter = retryAfter;
    }

    public int getRetryAfter() {
        return retryAfter;
    }
}
