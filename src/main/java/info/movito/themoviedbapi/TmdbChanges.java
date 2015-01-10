package info.movito.themoviedbapi;

import info.movito.themoviedbapi.tools.MovieDbException;


public class TmdbChanges extends AbstractTmdbApi {

    TmdbChanges(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public void getMovieChangesList(int page, String startDate, String endDate) {
        throw new MovieDbException("Not implemented yet");
    }


    public void getPersonChangesList(int page, String startDate, String endDate) {
        throw new MovieDbException("Not implemented yet");
    }
}
