package info.movito.themoviedbapi;

import info.movito.themoviedbapi.tools.MovieDbException;

/**
 * The movie database api for changes. See the
 * <a href="https://developer.themoviedb.org/reference/changes-movie-list">documentation</a> for more info.
 */
public class TmdbChanges extends AbstractTmdbApi {
    /**
     * Create a new TmdbChanges instance to call the changes related TMDb API methods.
     */
    TmdbChanges(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * TODO: implement.
     */
    public void getMovieChangesList(int page, String startDate, String endDate) {
        throw new MovieDbException("Not implemented yet");
    }

    /**
     * TODO: implement.
     */
    public void getPersonChangesList(int page, String startDate, String endDate) {
        throw new MovieDbException("Not implemented yet");
    }
}
