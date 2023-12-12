package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.Collection;
import uk.co.conoregan.themoviedbapi.model.Company;
import uk.co.conoregan.themoviedbapi.model.core.ResultsPage;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

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
     * This method is used to retrieve the basic information about a production company on TMDb.
     */
    public Company getCompanyInfo(int companyId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COMPANY, companyId);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Company.class);
    }

    /**
     * This method is used to retrieve the movies associated with a company.
     *
     * These movies are returned in order of most recently released to oldest. The default response will return 20
     */
    public CollectionResultsPage getCompanyMovies(int companyId, String language, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COMPANY, companyId, "movies");
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CollectionResultsPage.class);
    }

    /**
     * Collection Results Page.
     */
    public static class CollectionResultsPage extends ResultsPage<Collection> {

    }
}
