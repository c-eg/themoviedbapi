package info.movito.themoviedbapi.tools;

public class MovieDbException extends RuntimeException {


    private final Integer statusCode;
    private final MovieDbExceptionType exceptionType;
    private final String description;


    public MovieDbException(MovieDbExceptionType exceptionType, final String description) {
        this(exceptionType, description, null);
    }

    public MovieDbException(MovieDbExceptionType exceptionType, final String description, Integer statusCode) {
        this(null, exceptionType, statusCode, description);
    }

    public MovieDbException(final Throwable cause, final String description, MovieDbExceptionType exceptionType) {
        this(cause, exceptionType, null, description);
    }

    public MovieDbException(Throwable cause, MovieDbExceptionType exceptionType, Integer statusCode, String description) {
        super(cause);

        this.exceptionType = exceptionType;
        this.statusCode = statusCode;
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
                "statusCode=" + statusCode +
                ", exceptionType=" + exceptionType +
                ", description='" + description + '\'' +
                '}';
    }
}
