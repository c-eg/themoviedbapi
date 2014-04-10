package info.movito.themoviedbapi.tools;

public class MovieDbException extends RuntimeException {


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
