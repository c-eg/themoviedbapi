package info.movito.themoviedbapi.tools;

public class MovieDbException extends RuntimeException {


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
        /*
         * Image was invalid
         */
        INVALID_IMAGE,
        /*
         * Autorisation rejected
         */
        AUTHORISATION_FAILURE,
        /*
         * Service Unavailable, usually temporary
         */
        HTTP_503_ERROR,

        METHOD_NOT_YET_IMPLEMENTED
    }


    private final MovieDbExceptionType exceptionType;
    private final String description;


    public MovieDbException(final MovieDbExceptionType exceptionType, final String description) {
        this.exceptionType = exceptionType;
        this.description = description;
    }


    public MovieDbException(final MovieDbExceptionType exceptionType, final String description, final Throwable cause) {
        super(cause);
        this.exceptionType = exceptionType;
        this.description = description;
    }


    public MovieDbExceptionType getExceptionType() {
        return exceptionType;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "MovieDbException{" +
                "type=" + exceptionType +
                ", description='" + description + '\'' +
                "}\n " + super.toString();
    }
}
