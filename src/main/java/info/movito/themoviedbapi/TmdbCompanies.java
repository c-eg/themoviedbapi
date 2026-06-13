package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.companies.AlternativeNamesResultsPage;
import info.movito.themoviedbapi.model.companies.Company;
import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbApiClient;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for companies. See the
 * <a href="https://developer.themoviedb.org/reference/company-details">documentation</a> for more info.
 */
public class TmdbCompanies {
    protected static final String TMDB_METHOD_COMPANY = "company";

    private final TmdbApiClient tmdbApiClient;

    /**
     * Create a new TmdbCompany instance to call the company related TMDb API methods.
     */
    TmdbCompanies(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Get the company details by ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/company-details">documentation</a> for more info.</p>
     *
     * @param companyId The company ID
     * @return The company details
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Company getDetails(Integer companyId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COMPANY, companyId);
        return tmdbApiClient.get(apiUrl, Company.class);
    }

    /**
     * <p>Gets the alternative company names by ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/company-alternative-names">documentation</a> for more info.</p>
     *
     * @param companyId The company ID
     * @return The alternative company names
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AlternativeNamesResultsPage getAlternativeNames(Integer companyId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COMPANY, companyId, "alternative_names");
        return tmdbApiClient.get(apiUrl, AlternativeNamesResultsPage.class);
    }

    /**
     * <p>Get the company logos by ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/company-images">documentation</a> for more info.</p>
     *
     * @param companyId The company ID
     * @return The company logos
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ImageResults getImages(Integer companyId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COMPANY, companyId, "images");
        return tmdbApiClient.get(apiUrl, ImageResults.class);
    }
}
