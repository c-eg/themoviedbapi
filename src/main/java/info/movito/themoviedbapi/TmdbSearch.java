package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Collection;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.core.MovieResults;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.tv.TvSeries;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class TmdbSearch extends AbstractApiElement {

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
    public MovieResults searchMovie(String query, Integer searchYear, String language, boolean includeAdult, Integer page) {
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

        return mapJsonResult(apiUrl, MovieResults.class);
    }


    /**
     * Search Movies This is a good starting point to start finding movies on TMDb.
     *
     * @param query
     * @param language The language to include. Can be blank/null.
     * @param page     The page of results to return. 0 to get the default (first page)
     */
    public List<TvSeries> searchTv(String query, String language, Integer page) {
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

        return mapJsonResult(apiUrl, SeriesResults.class).getResults();
    }


    public static class SeriesResults extends ResultsPage<TvSeries> {

    }


    /**
     * Search for collections by name.
     *
     * @param query
     * @param language
     * @param page
     */
    public List<Collection> searchCollection(String query, String language, Integer page) {
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

        return mapJsonResult(apiUrl, CollectionResults.class).getResults();
    }


    /**
     * This is a good starting point to start finding people on TMDb.
     * <p/>
     * The idea is to be a quick and light method so you can iterate through people quickly.
     *
     * @param query
     * @param includeAdult
     * @param page
     */
    public List<Person> searchPerson(String query, boolean includeAdult, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TmdbPeople.TMDB_METHOD_PERSON);

        apiUrl.addParam(PARAM_QUERY, query);

        apiUrl.addParam(PARAM_ADULT, includeAdult);

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, page);
        }

        return mapJsonResult(apiUrl, TmdbPeople.PersonResults.class).getResults();
    }


    /**
     * Search for lists by name and description.
     *
     * @param query
     * @param language
     * @param page
     */
    public List<MovieList> searchList(String query, String language, Integer page) {
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

        return mapJsonResult(apiUrl, TmdbAccount.MovieListResults.class).getResults();
    }


    /**
     * Search Companies.
     * <p/>
     * You can use this method to search for production companies that are part of TMDb. The company IDs will map to those returned
     * on movie calls.
     * <p/>
     * http://help.themoviedb.org/kb/api/search-companies
     *
     * @param companyName
     * @param page
     */
    public List<Company> searchCompany(String companyName, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, "company");
        apiUrl.addParam(PARAM_QUERY, companyName);

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, page);
        }

        return mapJsonResult(apiUrl, CompanyResults.class).getResults();
    }


    /**
     * Search for keywords by name
     *
     * @param query
     * @param page
     */
    public List<Keyword> searchKeyword(String query, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, "keyword");

        if (StringUtils.isNotBlank(query)) {
            apiUrl.addParam(PARAM_QUERY, query);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, KeywordResults.class).getResults();
    }


    static class KeywordResults extends ResultsPage<Keyword> {

    }


    static class CompanyResults extends ResultsPage<Company> {

    }


    private static class CollectionResults extends ResultsPage<Collection> {

    }

}
