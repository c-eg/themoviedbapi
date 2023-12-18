package info.movito.themoviedbapi.tools;

/**
 * TMDb-API related exceptions.
 */
public class TmdbException extends Exception {
    public TmdbException(String message) {
        super(message);
    }

    public TmdbException(String message, Throwable cause) {
        super(message, cause);
    }
}
