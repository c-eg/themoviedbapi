package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeName;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.core.Results;
import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for companies. See the
 * <a href="https://developer.themoviedb.org/reference/company-details">documentation</a> for more info.
 */
public class TmdbCompanies extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_COMPANY = "company";

    /**
     * Create a new TmdbCompany instance to call the company related TMDb API methods.
     */
    TmdbCompanies(TmdbApi tmdbApi) {
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
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COMPANY, companyId);
        return mapJsonResult(apiUrl, Company.class);
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
        return mapJsonResult(apiUrl, AlternativeNamesResultsPage.class);
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
        return mapJsonResult(apiUrl, ImageResults.class);
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
    public static class AlternativeNamesResultsPage extends Results<AlternativeName> {
    }
}
