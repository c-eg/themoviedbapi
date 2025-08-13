package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.multi.MultiResultsPage;
import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.model.search.CollectionResultsPage;
import info.movito.themoviedbapi.model.search.CompanyResultsPage;
import info.movito.themoviedbapi.model.search.KeywordResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.TmdbCollections.TMDB_METHOD_COLLECTION;
import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.TmdbPeople.TMDB_METHOD_PERSON;
import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;

/**
 * The movie database api for searching. See the
 * <a href="https://developer.themoviedb.org/reference/search-collection">documentation</a> for more info.
 */
public class TmdbSearch extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_SEARCH = "search";
    protected static final String TMDB_METHOD_MULTI = "multi";

    private static final String PARAM_QUERY = "query";

    /**
     * Create a new TmdbSearch instance to call the search TMDb API methods.
     */
    public TmdbSearch(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Search for collections by their original, translated and alternative names.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-collection">documentation</a> for more info.</p>
     *
     * @param query        The query to search for.
     * @param language     nullable - The language to query the results in. Default: en-US.
     * @param includeAdult nullable - Whether to include adult results in the search.
     * @param page         nullable - The page of results to return. Default: 1.
     * @param region       nullable - The region (ISO-3166-1 code) to display the results for. e.g. "US".
     * @return The collection results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public CollectionResultsPage searchCollection(String query, String language, Boolean includeAdult, Integer page, String region)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TMDB_METHOD_COLLECTION)
            .addPathParam(PARAM_QUERY, query)
            .addQueryParam("include_adult", includeAdult)
            .addQueryParam("region", region)
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, CollectionResultsPage.class);
    }

    /**
     * <p>Search for companies by their original and alternative names.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-company">documentation</a> for more info.</p>
     *
     * @param query The query to search for.
     * @param page  nullable - The page of results to return. Default: 1.
     * @return The company results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public CompanyResultsPage searchCompany(String query, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, "company")
            .addPathParam(PARAM_QUERY, query)
            .addPage(page);
        return mapJsonResult(apiUrl, CompanyResultsPage.class);
    }

    /**
     * <p>Search for keywords by their name.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-keyword">documentation</a> for more info.</p>
     *
     * @param query The query to search for.
     * @param page  nullable - The page of results to return. Default: 1.
     * @return The keyword results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public KeywordResultsPage searchKeyword(String query, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, "keyword")
            .addPathParam(PARAM_QUERY, query)
            .addPage(page);
        return mapJsonResult(apiUrl, KeywordResultsPage.class);
    }

    /**
     * <p>Search for movies by their original, translated and alternative titles.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-movie">documentation</a> for more info.</p>
     *
     * @param query              The query to search for.
     * @param includeAdult       nullable - Whether to include adult results in the search.
     * @param language           nullable - The language to query the results in. Default: en-US.
     * @param primaryReleaseYear nullable - Filter the results so that only the primary release year matches this value.
     * @param page               nullable - The page of results to return. Default: 1.
     * @param region             nullable - The region (ISO-3166-1 code) to display the results for. e.g. "US".
     * @param year               nullable - Filter the results so that only the release year matches this value.
     * @return The movie results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage searchMovie(String query, Boolean includeAdult, String language, String primaryReleaseYear, Integer page,
                                        String region, String year) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TMDB_METHOD_MOVIE)
            .addPathParam(PARAM_QUERY, query)
            .addQueryParam("include_adult", includeAdult)
            .addQueryParam("primary_release_year", primaryReleaseYear)
            .addQueryParam("region", region)
            .addQueryParam("year", year)
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Use multi search when you want to search for movies, TV shows and people in a single request.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-multi">documentation</a> for more info.</p>
     *
     * @param query        The query to search for.
     * @param includeAdult nullable - Whether to include adult results in the search.
     * @param language     nullable - The language to query the results in. Default: en-US.
     * @param page         nullable - The page of results to return. Default: 1.
     * @return The multi results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MultiResultsPage searchMulti(String query, Boolean includeAdult, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TMDB_METHOD_MULTI)
            .addPathParam(PARAM_QUERY, query)
            .addQueryParam("include_adult", includeAdult)
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, MultiResultsPage.class);
    }

    /**
     * <p>Search for people by their name and also known as names.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-person">documentation</a> for more info.</p>
     *
     * @param query        The query to search for.
     * @param includeAdult nullable - Whether to include adult results in the search.
     * @param language     nullable - The language to query the results in. Default: en-US.
     * @param page         nullable - The page of results to return. Default: 1.
     * @return The person results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PopularPersonResultsPage searchPerson(String query, Boolean includeAdult, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TMDB_METHOD_PERSON)
            .addPathParam(PARAM_QUERY, query)
            .addQueryParam("include_adult", includeAdult)
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, PopularPersonResultsPage.class);
    }

    /**
     * <p>Search for TV shows by their original, translated and also known as names.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/search-tv">documentation</a> for more info.</p>
     *
     * @param query            The query to search for.
     * @param firstAirDateYear nullable - Filter the results so that only the first air date year matches this value.
     * @param includeAdult     nullable - Whether to include adult results in the search.
     * @param language         nullable - The language to query the results in. Default: en-US.
     * @param page             nullable - The page of results to return. Default: 1.
     * @param year             nullable - Filter the results so that only the release year matches this value.
     * @return The TV series results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage searchTv(String query, Integer firstAirDateYear, Boolean includeAdult, String language, Integer page,
                                        Integer year) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_SEARCH, TMDB_METHOD_TV)
            .addPathParam(PARAM_QUERY, query)
            .addQueryParam("first_air_date_year", firstAirDateYear)
            .addQueryParam("include_adult", includeAdult)
            .addQueryParam("year", year)
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }
}
