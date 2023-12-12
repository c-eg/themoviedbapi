package uk.co.conoregan.themoviedbapi.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.Genre;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;
import uk.co.conoregan.themoviedbapi.model.core.MovieResultsPage;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import java.util.List;

/**
 * The movie database api for genre. See the
 * <a href="https://developer.themoviedb.org/reference/genre-movie-list">documentation</a> for more info.
 */
public class TmdbGenre extends AbstractTmdbApi {
    private static final String TMDB_METHOD_GENRE = "genre";

    private static final String PARAM_INCLUDE_ALL_MOVIES = "include_all_movies";

    /**
     * Create a new TmdbGenre instance to call the genre related TMDb API methods.
     */
    public TmdbGenre(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * You can use this method to retrieve the list of genres used on TMDb.
     * These IDs will correspond to those found in movie calls.
     *
     * @deprecated use {@code getMovieGenreList} as TV shows Genres was added.
     */
    @Deprecated
    public List<Genre> getGenreList(String language) throws TmdbException {
        return getMovieGenreList(language);
    }

    /**
     * You can use this method to retrieve the list of official genres for
     * movies used on TMDb.
     *
     * These IDs will correspond to those found in movie calls.
     */
    public List<Genre> getMovieGenreList(String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_GENRE, "movie", "list");
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Genres.class).genres;
    }

    /**
     * You can use this method to retrieve the list of of official genres for
     * TV shows used on TMDb.
     *
     * These IDs will correspond to those found in TV shows calls.
     */
    public List<Genre> getTvGenreList(String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_GENRE, "tv", "list");
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Genres.class).genres;
    }

    /**
     * Get a list of movies per genre.
     *
     * It is important to understand that only movies with more than 10 votes get listed.
     *
     * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
     */
    public MovieResultsPage getGenreMovies(int genreId, String language, Integer page, boolean includeAllMovies) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_GENRE, genreId, "movies");

        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addPathParam(PARAM_INCLUDE_ALL_MOVIES, includeAllMovies);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieResultsPage.class);
    }

    private static class Genres extends AbstractJsonMapping {
        @JsonProperty("genres")
        private List<Genre> genres;
    }
}
