package uk.co.conoregan.themoviedbapi;

import uk.co.conoregan.themoviedbapi.model.credits.Credit;
import uk.co.conoregan.themoviedbapi.tools.ApiUrl;
import uk.co.conoregan.themoviedbapi.tools.TmdbApiClient;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for credits. See the
 * <a href="https://developer.themoviedb.org/reference/credit-details">documentation</a> for more info.
 */
public class TmdbCredits {
    protected static final String TMDB_METHOD_CREDITS = "credit";

    private final TmdbApiClient tmdbApiClient;

    /**
     * Create a new TmdbCredits instance to call the credits related TMDb API methods.
     */
    TmdbCredits(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Get the details for a credit.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/credit-details">documentation</a> for more info.</p>
     *
     * @param creditId The credit id.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @return the credit details
     */
    public Credit getDetails(String creditId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_CREDITS, creditId)
            .addLanguage(language);

        return tmdbApiClient.get(apiUrl, Credit.class);
    }
}
