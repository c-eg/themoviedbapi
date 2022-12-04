package info.movito.themoviedbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;

import java.util.List;


public class TmdbGenre extends AbstractTmdbApi {

    public static final String PARAM_INCLUDE_ALL_MOVIES = "include_all_movies";
    public static final String TMDB_METHOD_GENRE = "genre";


    public TmdbGenre(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * You can use this method to retrieve the list of genres used on TMDb.
     * 
     * These IDs will correspond to those found in movie calls.
     */
    public List<Genre> getGenreList(String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GENRE, "list");

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, Genres.class).genres;
    }


    private static class Genres extends AbstractJsonMapping {

        @JsonProperty("genres")
        private List<Genre> genres;

    }


    /**
     * Get a list of movies per genre.
     * 
     * It is important to understand that only movies with more than 10 votes get listed.
     * 
     * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
     */
    public MovieResultsPage getGenreMovies(int genreId, String language, Integer page, boolean includeAllMovies) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GENRE, genreId, "movies");

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        apiUrl.addParam(PARAM_INCLUDE_ALL_MOVIES, includeAllMovies);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }
}
