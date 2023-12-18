package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.MovieListResultsPage;
import info.movito.themoviedbapi.model.TvSeriesDbResultsPage;
import info.movito.themoviedbapi.model.Collection;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.core.MovieDbResultsPage;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.TmdbLists.TMDB_METHOD_LIST;
import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The movie database api for searching. See the
 * <a href="https://developer.themoviedb.org/reference/search-collection">documentation</a> for more info.
 */
public class TmdbSearch extends AbstractTmdbApi {
    public static final String TMDB_METHOD_SEARCH = "search";

    public static final Object TMDB_METHOD_MULTI = "multi";

    private static final String PARAM_QUERY = "query";

    /**
     * Create a new TmdbSearch instance to call the search TMDb API methods.
     */
    public TmdbSearch(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Search Movies This is a good starting point to start finding movies on TMDb.
     *
     * @param query        The query to search for.
     * @param searchYear   Limit the search to the provided year. Zero (0) will get all years.
     * @param language     The language to include. Can be blank/null.
     * @param includeAdult Whether to include adult titles in the search.
     * @param page         The page of results to return. 0 to get the default (first page).
     */
    public MovieDbResultsPage searchMovie(String query, Integer searchYear, String language, boolean includeAdult,
                                          Integer page) throws TmdbException {
        if (isBlank(query)) {
            throw new RuntimeException("query must not be blank");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, TMDB_METHOD_MOVIE);
        apiEndpoint.addPathParam(PARAM_QUERY, query);

        // optional parameters
        if (searchYear != null && searchYear > 0) {
            apiEndpoint.addPathParam(PARAM_YEAR, Integer.toString(searchYear));
        }

        apiEndpoint.addLanguage(language);
        apiEndpoint.addPathParam(PARAM_ADULT, Boolean.toString(includeAdult));
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieDbResultsPage.class);
    }

    /**
     * Search for TV shows by title.
     *
     * @param query        The query to search for.
     * @param searchYear   Limit the search to the provided year. Zero (0) will get all years.
     * @param language     The language to include. Can be blank/null.
     * @param includeAdult Whether to include adult titles in the search.
     * @param page         The page of results to return. 0 to get the default (first page).
     */
    public TvSeriesDbResultsPage searchTv(String query, Integer searchYear, String language, boolean includeAdult,
                                          Integer page) throws TmdbException {
        if (isBlank(query)) {
            throw new RuntimeException("query must not be blank");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, TmdbTV.TMDB_METHOD_TV);
        apiEndpoint.addPathParam(PARAM_QUERY, query);

        // optional parameters
        if (searchYear != null && searchYear > 0) {
            apiEndpoint.addPathParam(PARAM_YEAR, Integer.toString(searchYear));
        }

        apiEndpoint.addLanguage(language);
        apiEndpoint.addPathParam(PARAM_ADULT, Boolean.toString(includeAdult));
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesDbResultsPage.class);
    }

    /**
     * Search for collections by name.
     *
     * @param query        The query to search for.
     * @param language     The language to include. Can be blank/null.
     * @param includeAdult Whether to include adult titles in the search.
     * @param page         The page of results to return. 0 to get the default (first page).
     */
    public CollectionResultsPage searchCollection(String query, String language, boolean includeAdult, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, TmdbCollections.TMDB_METHOD_COLLECTION);

        // TODO: check this shouldn't be like the ones above
        if (isNotBlank(query)) {
            apiEndpoint.addPathParam(PARAM_QUERY, query);
        }

        apiEndpoint.addLanguage(language);
        apiEndpoint.addPathParam(PARAM_ADULT, Boolean.toString(includeAdult));
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CollectionResultsPage.class);
    }

    /**
     * This is a good starting point to start finding people on TMDb.
     *
     * The idea is to be a quick and light method so you can iterate through people quickly.
     */

    public TmdbPeople.PersonResultsPage searchPerson(String query, boolean includeAdult, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, TmdbPeople.TMDB_METHOD_PERSON);

        apiEndpoint.addPathParam(PARAM_QUERY, query);
        apiEndpoint.addPathParam(PARAM_ADULT, includeAdult);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TmdbPeople.PersonResultsPage.class);
    }

    /**
     * Search for lists by name and description.
     */
    public MovieListResultsPage searchList(String query, String language, Integer page) throws TmdbException {
        System.err.println("This method is part of the API but seems currently not available. " +
            "See https://www.themoviedb.org/talk/593409e3c3a36859ef01eddb#597124f8c3a3681608008424");

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, TMDB_METHOD_LIST);

        // TODO: check this shouldn't be like the ones above
        if (isNotBlank(query)) {
            apiEndpoint.addPathParam(PARAM_QUERY, query);
        }

        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieListResultsPage.class);
    }

    /**
     * Search Companies.
     *
     * You can use this method to search for production companies that are part of TMDb. The company IDs will map to
     * those returned on movie calls.
     *
     * http://help.themoviedb.org/kb/api/search-companies
     */
    public CompanyResultsPage searchCompany(String companyName, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, "company");
        apiEndpoint.addPathParam(PARAM_QUERY, companyName);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CompanyResultsPage.class);
    }

    /**
     * Search for keywords by name.
     */
    public KeywordResultsPage searchKeyword(String query, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, "keyword");

        // TODO: check this shouldn't be like the ones above
        if (isNotBlank(query)) {
            apiEndpoint.addPathParam(PARAM_QUERY, query);
        }

        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, KeywordResultsPage.class);
    }

    /**
     * Search the movie, tv show and person collections with a single query.
     * <p>Each mapped result is the same response you would get from each independent search.</p>
     *
     * @return ResultsPage of Multi.
     */
    public MultiListResultsPage searchMulti(String query, String language, Integer page) throws TmdbException {
        if (isBlank(query)) {
            throw new RuntimeException("query must not be blank");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_SEARCH, TMDB_METHOD_MULTI);
        apiEndpoint.addPathParam(PARAM_QUERY, query);

        // optional parameters
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MultiListResultsPage.class);
    }

    // CHECKSTYLE OFF: MissingJavadocType
    public static class KeywordResultsPage extends ResultsPage<Keyword> {

    }

    public static class CompanyResultsPage extends ResultsPage<Company> {

    }

    public static class CollectionResultsPage extends ResultsPage<Collection> {

    }

    public static class MultiListResultsPage extends ResultsPage<Multi> {

    }
    // CHECKSTYLE ON: MissingJavadocType
}
