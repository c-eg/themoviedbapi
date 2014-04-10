package info.movito.themoviedbapi.tools;

/**
 * Status codes that further categorize a <code>MovieDbException</code>.
 */
public enum MovieDbExceptionType {
    /*
     * Unknown error occurred
     */
    UNKNOWN_CAUSE,
    /*
     * URL is invalid
     */
    INVALID_URL,
    /*
     * Page not found
     */
    HTTP_404_ERROR,
    /*
     * The movie id was not found
     */
    INVALID_ID,
    /*
     * Mapping failed from target to internal objects
     */
    MAPPING_FAILED,
    /*
     * Error connecting to the service
     */
    CONNECTION_ERROR,
    /**
     * Error stream was null and http reported error status code (should not happen, but does)
     */
    ERR_STREAM_NULL,
    /*
     * Image was invalid
     */
    INVALID_IMAGE,
    /*
     * Authorisation rejected
     */
    AUTHORISATION_FAILURE,
    /*
     * Service Unavailable, usually temporary
     */
    HTTP_503_ERROR,

    METHOD_NOT_YET_IMPLEMENTED
}
