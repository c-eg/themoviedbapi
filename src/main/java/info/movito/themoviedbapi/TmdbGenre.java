package info.movito.themoviedbapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for genre. See the
 * <a href="https://developer.themoviedb.org/reference/genre-movie-list">documentation</a> for more info.
 */
public class TmdbGenre extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_GENRE = "genre";

    /**
     * Create a new TmdbGenre instance to call the genre related TMDb API methods.
     */
    public TmdbGenre(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the list of official genres for movies.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/genre-movie-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language, e.g. "en".
     * @return The list of official genres for movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Genre> getMovieList(String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GENRE, "movie/list");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, Genres.class).genres;
    }

    /**
     * <p>Get the list of official genres for TV shows.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/genre-tv-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language, e.g. "en".
     * @return The list of official genres for movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Genre> getTvList(String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GENRE, "tv/list");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, Genres.class).genres;
    }

    private static class Genres extends AbstractJsonMapping {
        @JsonProperty("genres")
        private List<Genre> genres;
    }
}
