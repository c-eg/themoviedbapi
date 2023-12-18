package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.ConfigResults;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for configuration. See the
 * <a href="https://developer.themoviedb.org/reference/configuration-details">documentation</a> for more info.
 */
public class TmdbConfig extends AbstractTmdbApi {
    private static final String TMDB_METHOD_CONFIGURATION = "configuration";

    /**
     * Create a new TmdbConfig instance to call the config related TMDb API methods.
     */
    TmdbConfig(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Query the API configuration details.
     */
    public ConfigResults getConfig() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ConfigResults.class);
    }
}
