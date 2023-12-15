package uk.co.conoregan.themoviedbapi.tools;

import lombok.Getter;

/**
 * Exception that is thrown if the request limit is reached.
 */
@Getter
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
}
