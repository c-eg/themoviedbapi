package info.movito.themoviedbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.core.MovieResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class TmdbGenre extends AbstractApiElement {

    public static final String PARAM_INCLUDE_ALL_MOVIES = "include_all_movies";
    public static final String TMDB_METHOD_GENRE = "genre";


    TmdbGenre(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * You can use this method to retrieve the list of genres used on TMDb.
     * <p/>
     * These IDs will correspond to those found in movie calls.
     *
     * @param language
     */
    public List<Genre> getGenreList(String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GENRE, "list");

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        return mapJsonResult(apiUrl, Genres.class).genres;
    }


    private static class Genres extends AbstractJsonMapping {

        @JsonProperty("genres")
        private List<Genre> genres;

    }


    /**
     * Get a list of movies per genre.
     * <p/>
     * It is important to understand that only movies with more than 10 votes get listed.
     * <p/>
     * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
     *
     * @param genreId
     * @param language
     * @param page
     */
    public MovieResults getGenreMovies(int genreId, String language, Integer page, boolean includeAllMovies) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GENRE, genreId, "movies");

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, page);
        }

        apiUrl.addParam(PARAM_INCLUDE_ALL_MOVIES, includeAllMovies);

        return mapJsonResult(apiUrl, MovieResults.class);
    }
}
