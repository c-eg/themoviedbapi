package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.multi.MultiResultsPage;
import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.model.time.TimeWindow;

/**
 * The movie database api for trending media. See the
 * <a href="https://developer.themoviedb.org/reference/trending-all">documentation</a> for more info.
 */
public class TmdbTrending extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_TRENDING = "trending";

    private static final String TMDB_PARAM_TIME_WINDOW = "time_window";

    /**
     * Create a new TmdbTrending instance to call the rending TMDb API methods.
     */
    TmdbTrending(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the trending movies, TV shows and people.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/trending-all">documentation</a> for more info.</p>
     *
     * @param timeWindow The time window for the trending media.
     * @param language   optional - The language to query the results in. Default: en-US.
     * @return The trending media.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MultiResultsPage getAll(TimeWindow timeWindow, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TRENDING, "all");
        apiUrl.addPathParam(TMDB_PARAM_TIME_WINDOW, timeWindow.getValue());
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, MultiResultsPage.class);
    }

    /**
     * <p>Get the trending movies on TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/trending-movies">documentation</a> for more info.</p>
     *
     * @param timeWindow The time window for the trending movie.
     * @param language   optional - The language to query the results in. Default: en-US.
     * @return The trending movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getMovies(TimeWindow timeWindow, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TRENDING, "movie");
        apiUrl.addPathParam(TMDB_PARAM_TIME_WINDOW, timeWindow.getValue());
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get the trending people on TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/trending-people">documentation</a> for more info.</p>
     *
     * @param timeWindow The time window for the trending people.
     * @param language   optional - The language to query the results in. Default: en-US.
     * @return The trending people.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PopularPersonResultsPage getPeople(TimeWindow timeWindow, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TRENDING, "person");
        apiUrl.addPathParam(TMDB_PARAM_TIME_WINDOW, timeWindow.getValue());
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, PopularPersonResultsPage.class);
    }

    /**
     * <p>Get the trending TV shows on TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/trending-tv">documentation</a> for more info.</p>
     *
     * @param timeWindow The time window for the trending tv.
     * @param language   optional - The language to query the results in. Default: en-US.
     * @return The trending tv.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getTv(TimeWindow timeWindow, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TRENDING, "tv");
        apiUrl.addPathParam(TMDB_PARAM_TIME_WINDOW, timeWindow.getValue());
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }
}
