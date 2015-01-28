package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Collection;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;


public class TmdbSearch extends AbstractTmdbApi {

    public static final String TMDB_METHOD_SEARCH = "search";
    private static final String PARAM_QUERY = "query";


    public TmdbSearch(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * Search Movies This is a good starting point to start finding movies on TMDb.
     *
     * @param query
     * @param searchYear   Limit the search to the provided year. Zero (0) will get all years
     * @param language     The language to include. Can be blank/null.
     * @param includeAdult true or false to include adult titles in the search
     * @param page         The page of results to return. 0 to get the default (first page)
     */
    public MovieResultsPage searchMovie(String query, Integer searchYear, String language, boolean includeAdult, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TmdbMovies.TMDB_METHOD_MOVIE);

        if (StringUtils.isBlank(query)) {
            throw new RuntimeException("query must not be blank");
        }

        apiUrl.addParam(PARAM_QUERY, query);

        // optional parameters

        if (searchYear != null && searchYear > 0) {
            apiUrl.addParam(PARAM_YEAR, Integer.toString(searchYear));
        }

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        apiUrl.addParam(PARAM_ADULT, Boolean.toString(includeAdult));

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }


    /**
     * Search Movies This is a good starting point to start finding movies on TMDb.
     *  @param query
     * @param language The language to include. Can be blank/null.
     * @param page     The page of results to return. 0 to get the default (first page)
     */
    public TvResultsPage searchTv(String query, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TmdbTV.TMDB_METHOD_TV);

        if (StringUtils.isBlank(query)) {
            throw new RuntimeException("query must not be blank");
        }

        apiUrl.addParam(PARAM_QUERY, query);

        // optional parameters

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    /**
     * Search for collections by name.
     *  @param query
     * @param language
     * @param page
     */
    public CollectionResultsPage searchCollection(String query, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TmdbCollections.TMDB_METHOD_COLLECTION);

        if (StringUtils.isNotBlank(query)) {
            apiUrl.addParam(PARAM_QUERY, query);
        }

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, CollectionResultsPage.class);
    }


    /**
     * This is a good starting point to start finding people on TMDb.
     * <p/>
     * The idea is to be a quick and light method so you can iterate through people quickly.
     *  @param query
     * @param includeAdult
     * @param page
     */
    public TmdbPeople.PersonResultsPage searchPerson(String query, boolean includeAdult, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TmdbPeople.TMDB_METHOD_PERSON);

        apiUrl.addParam(PARAM_QUERY, query);

        apiUrl.addParam(PARAM_ADULT, includeAdult);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TmdbPeople.PersonResultsPage.class);
    }


    /**
     * Search for lists by name and description.
     *  @param query
     * @param language
     * @param page
     */
    public TmdbAccount.MovieListResultsPage searchList(String query, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TmdbLists.TMDB_METHOD_LIST);

        if (StringUtils.isNotBlank(query)) {
            apiUrl.addParam(PARAM_QUERY, query);
        }

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, TmdbAccount.MovieListResultsPage.class);
    }


    /**
     * Search Companies.
     * <p/>
     * You can use this method to search for production companies that are part of TMDb. The company IDs will map to
     * those returned on movie calls.
     * <p/>
     * http://help.themoviedb.org/kb/api/search-companies
     *  @param companyName
     * @param page
     */
    public CompanyResultsPage searchCompany(String companyName, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, "company");
        apiUrl.addParam(PARAM_QUERY, companyName);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, CompanyResultsPage.class);
    }


    /**
     * Search for keywords by name
     *  @param query
     * @param page
     */
    public KeywordResultsPage searchKeyword(String query, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, "keyword");

        if (StringUtils.isNotBlank(query)) {
            apiUrl.addParam(PARAM_QUERY, query);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, KeywordResultsPage.class);
    }


    static class KeywordResultsPage extends ResultsPage<Keyword> {

    }


    static class CompanyResultsPage extends ResultsPage<Company> {

    }


    public static class CollectionResultsPage extends ResultsPage<Collection> {

    }

}
