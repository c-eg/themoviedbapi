package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.ConfigResults;
import info.movito.themoviedbapi.tools.ApiUrl;

/**
 * The movie database api for configuration. See the
 * <a href="https://developer.themoviedb.org/reference/configuration-details">documentation</a> for more info.
 */
class TmdbConfig extends AbstractTmdbApi {
    public static final String TMDB_METHOD_CONFIGURATION = "configuration";

    /**
     * Create a new TmdbConfig instance to call the config related TMDb API methods.
     */
    TmdbConfig(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    public ConfigResults getConfig() {
        return mapJsonResult(new ApiUrl(TMDB_METHOD_CONFIGURATION), ConfigResults.class);
    }
}
