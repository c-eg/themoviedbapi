package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Collection;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;


public class TmdbCompany extends AbstractTmdbApi {

    public static final String TMDB_METHOD_COMPANY = "company";


    TmdbCompany(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * This method is used to retrieve the basic information about a production company on TMDb.
     *
     * @param companyId
     */
    public Company getCompanyInfo(int companyId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COMPANY, companyId);

        return mapJsonResult(apiUrl, Company.class);
    }


    /**
     * This method is used to retrieve the movies associated with a company.
     * <p/>
     * These movies are returned in order of most recently released to oldest. The default response will return 20
     * movies per page.
     * <p/>
     * TODO: Implement more than 20 movies
     *
     * @param companyId
     * @param language
     * @param page
     */
    public CollectionResultsPage getCompanyMovies(int companyId, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COMPANY, companyId, "movies");

        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, CollectionResultsPage.class);
    }


    public static class CollectionResultsPage extends ResultsPage<Collection> {

    }
}
