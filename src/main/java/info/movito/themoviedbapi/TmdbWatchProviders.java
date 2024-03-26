package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.watchproviders.AvailableRegionResults;
import info.movito.themoviedbapi.model.watchproviders.ProviderResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for watch providers. See the
 * <a href="https://developer.themoviedb.org/reference/watch-providers-available-regions">documentation</a> for more info.
 */
public class TmdbWatchProviders extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_WATCH_PROVIDERS = "watch/providers";

    /**
     * Create a new TmdbWatchProviders instance to call the watch providers TMDb API methods.
     */
    TmdbWatchProviders(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the list of the countries we have watch provider (OTT/streaming) data for.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/watch-providers-available-regions">documentation</a>
     * for more info.</p>
     *
     * @param language nullable - The language, e.g. "en-US".
     */
    public AvailableRegionResults getAvailableRegions(String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_WATCH_PROVIDERS);
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, AvailableRegionResults.class);
    }

    /**
     * <p>Get the list of streaming providers we have for movies.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/watch-providers-movie-list">documentation</a>
     * for more info.</p>
     *
     * @param language    nullable - The language, e.g. "en-US".
     * @param watchRegion nullable - The watch region, e.g. "AD"
     */
    public ProviderResults getMovieProviders(String language, String watchRegion) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_WATCH_PROVIDERS, "movie");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParam("watch_region", watchRegion);
        return mapJsonResult(apiUrl, ProviderResults.class);
    }

    /**
     * <p>Get the list of streaming providers we have for TV shows.</p>
     * <p>In order to use this data you must attribute the source of the data as JustWatch. If TMDb find any usage not complying with these
     * terms TMDb will revoke access to the API.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/watch-provider-tv-list">documentation</a>
     * for more info.</p>
     *
     * @param language    nullable - The language, e.g. "en-US".
     * @param watchRegion nullable - The watch region, e.g. "AD".
     */
    public ProviderResults getTvProviders(String language, String watchRegion) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_WATCH_PROVIDERS, "tv");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParam("watch_region", watchRegion);
        return mapJsonResult(apiUrl, ProviderResults.class);
    }
}
