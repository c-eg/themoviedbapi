package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.movielists.MovieResultsPageWithDates;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for movie lists. See the
 * <a href="https://developer.themoviedb.org/reference/movie-now-playing-list">documentation</a> for more info.
 */
public class TmdbMovieLists extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_MOVIE_LISTS = "movie";

    /**
     * Create a new TmdbMovieLists instance to call the movie lists related TMDb API methods.
     */
    TmdbMovieLists(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get a list of movies that are currently in theatres.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-now-playing-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return.
     * @param region   nullable - The region (ISO-3166-1 code) to display the results for. e.g. "US".
     * @return The now playing movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPageWithDates getNowPlaying(String language, Integer page, String region) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE_LISTS, "now_playing");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addQueryParam("region", region);
        return mapJsonResult(apiUrl, MovieResultsPageWithDates.class);
    }

    /**
     * <p>Get a list of movies ordered by popularity.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-popular-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return.
     * @param region   nullable - The region (ISO-3166-1 code) to display the results for. e.g. "US".
     * @return The popular movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getPopular(String language, Integer page, String region) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE_LISTS, "popular");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addQueryParam("region", region);
        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get a list of movies ordered by rating.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-top-rated-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return.
     * @param region   nullable - The region (ISO-3166-1 code) to display the results for. e.g. "US".
     * @return The top-rated movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getTopRated(String language, Integer page, String region) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE_LISTS, "top_rated");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addQueryParam("region", region);
        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get a list of movies that are being released soon.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-upcoming-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return.
     * @param region   nullable - The region (ISO-3166-1 code) to display the results for. e.g. "US".
     * @return The upcoming movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPageWithDates getUpcoming(String language, Integer page, String region) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE_LISTS, "upcoming");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addQueryParam("region", region);
        return mapJsonResult(apiUrl, MovieResultsPageWithDates.class);
    }
}
