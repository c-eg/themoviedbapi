package info.movito.themoviedbapi;

import info.movito.themoviedbapi.tools.MovieDbException;


public class TmdbChanges extends AbstractApiElement {

    TmdbChanges(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public void getMovieChangesList(int page, String startDate, String endDate) {
        throw new MovieDbException(MovieDbException.MovieDbExceptionType.UNKNOWN_CAUSE, "Not implemented yet");
    }


    public void getPersonChangesList(int page, String startDate, String endDate) {
        throw new MovieDbException(MovieDbException.MovieDbExceptionType.UNKNOWN_CAUSE, "Not implemented yet");
    }
}
