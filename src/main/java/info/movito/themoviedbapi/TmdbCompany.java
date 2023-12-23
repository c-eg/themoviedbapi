package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeName;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.core.image.LogoImageResults;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for companies. See the
 * <a href="https://developer.themoviedb.org/reference/company-details">documentation</a> for more info.
 */
public class TmdbCompany extends AbstractTmdbApi {
    private static final String TMDB_METHOD_COMPANY = "company";

    /**
     * Create a new TmdbCompany instance to call the company related TMDb API methods.
     */
    TmdbCompany(TmdbApi tmdbApi) {
        super(tmdbApi);
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
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COMPANY, companyId);
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Company.class);
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
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COMPANY, companyId, "alternative_names");
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, AlternativeNamesResultsPage.class);
    }

    /**
     * <p>Get the company logos by ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/company-images">documentation</a> for more info.</p>
     *
     * @param companyId The company ID
     * @return The company logos
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public LogoImageResults getImages(Integer companyId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COMPANY, companyId, "images");
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, LogoImageResults.class);
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
    public static class AlternativeNamesResultsPage extends ResultsPage<AlternativeName> { }
}
