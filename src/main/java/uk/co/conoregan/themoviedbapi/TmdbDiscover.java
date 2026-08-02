package uk.co.conoregan.themoviedbapi;

import uk.co.conoregan.themoviedbapi.model.core.MovieResultsPage;
import uk.co.conoregan.themoviedbapi.model.core.TvSeriesResultsPage;
import uk.co.conoregan.themoviedbapi.tools.ApiUrl;
import uk.co.conoregan.themoviedbapi.tools.TmdbApiClient;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import uk.co.conoregan.themoviedbapi.tools.builders.discover.DiscoverTvParamBuilder;

/**
 * The movie database api for discover. See the
 * <a href="https://developer.themoviedb.org/reference/discover-movie">documentation</a> for more info.
 */
public class TmdbDiscover {
    protected static final String TMDB_METHOD_DISCOVER = "discover";
    protected static final String TMDB_METHOD_MOVIE = "movie";
    protected static final String TMDB_METHOD_TV = "tv";

    private final TmdbApiClient tmdbApiClient;

    /**
     * Create a new TmdbDiscover instance to call the discover related TMDb API methods.
     */
    TmdbDiscover(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Find movies using over 30 filters and sort options.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/discover-movie">documentation</a> for more info.</p>
     *
     * @param builder A discover object containing the search criteria wanted
     * @return the movie results page.
     */
    public MovieResultsPage getMovie(DiscoverMovieParamBuilder builder) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_DISCOVER, TMDB_METHOD_MOVIE)
            .addPathParams(builder);
        return tmdbApiClient.get(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Find TV shows using over 30 filters and sort options.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/discover-tv">documentation</a> for more info.</p>
     *
     * @param builder A discover object containing the search criteria wanted
     * @return the tv series results page.
     */
    public TvSeriesResultsPage getTv(DiscoverTvParamBuilder builder) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_DISCOVER, TMDB_METHOD_TV)
            .addPathParams(builder);
        return tmdbApiClient.get(apiUrl, TvSeriesResultsPage.class);
    }
}
