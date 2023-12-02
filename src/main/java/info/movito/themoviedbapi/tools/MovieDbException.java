package info.movito.themoviedbapi.tools;

/**
 * TMDb-API related exceptions. These can be either <code>ResponseStatusException</code> in case the tmdb server
 * responded with an error code, or just an instance of this class if client side processing failed for some reason.
 */
public class MovieDbException extends RuntimeException {
    public MovieDbException(String message) {
        super(message);
    }

    public MovieDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
