package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.credits.MediaCredit;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for credits. See the
 * <a href="https://developer.themoviedb.org/reference/credit-details">documentation</a> for more info.
 */
public class TmdbCredits extends AbstractTmdbApi {
    private static final String TMDB_METHOD_CREDITS = "credits";

    /**
     * Create a new TmdbConfig instance to call the config related TMDb API methods.
     */
    TmdbCredits(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Query the API configuration details.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/credit-details">documentation</a> for more info.</p>
     *
     * @param creditId The credit ID.
     * @return The configuration details
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MediaCredit getCredits(String creditId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CREDITS, creditId);
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MediaCredit.class);
    }
}
