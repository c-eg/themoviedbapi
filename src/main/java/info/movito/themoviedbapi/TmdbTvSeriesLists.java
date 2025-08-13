package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;

/**
 * The movie database api for tv series lists. See the
 * <a href="https://developer.themoviedb.org/reference/tv-series-airing-today-list">documentation</a> for more info.
 */
public class TmdbTvSeriesLists extends AbstractTmdbApi {
    /**
     * Create a new TmdbTvSeriesLists instance to call the tv series lists TMDb API methods.
     */
    TmdbTvSeriesLists(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get a list of TV shows airing today.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-airing-today-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @param timezone nullable - The timezone to use when querying the air dates.
     * @return a list of TV shows airing today.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getAiringToday(String language, Integer page, String timezone) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, "airing_today")
            .addLanguage(language)
            .addPage(page)
            .addQueryParam("timezone", timezone);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }

    /**
     * <p>Get a list of TV shows that air in the next 7 days.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-on-the-air-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @param timezone nullable - The timezone to use when querying the air dates.
     * @return a list of TV shows that air in the next 7 days.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getOnTheAir(String language, Integer page, String timezone) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, "on_the_air")
            .addLanguage(language)
            .addPage(page)
            .addQueryParam("timezone", timezone);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }

    /**
     * <p>Get a list of TV shows ordered by popularity.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-popular-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return a list of TV shows ordered by popularity.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getPopular(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, "popular")
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }

    /**
     * <p>Get a list of TV shows ordered by rating.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-top-rated-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return a list of TV shows ordered by rating.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getTopRated(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, "top_rated")
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }
}
