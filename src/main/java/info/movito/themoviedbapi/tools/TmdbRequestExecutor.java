package info.movito.themoviedbapi.tools;

/**
 * Interface for executing requests against the TMDB API.
 */
public interface TmdbRequestExecutor {
    /**
     * Executes the given request and returns the response.
     *
     * @param request the request to execute
     * @return the response from the movie database api
     * @throws TmdbException if the request could not be executed
     */
    TmdbResponse execute(TmdbRequest request) throws TmdbException;
}
