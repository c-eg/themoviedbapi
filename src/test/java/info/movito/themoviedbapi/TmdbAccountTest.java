package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;

import info.movito.themoviedbapi.model.account.Account;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import info.movito.themoviedbapi.tools.sortby.AccountSortBy;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbAccount.TMDB_METHOD_ACCOUNT;
import static info.movito.themoviedbapi.testutil.TestUtils.validateAbstractJsonMappingFields;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbAccount}.
 */
public class TmdbAccountTest extends AbstractTmdbApiTest<TmdbAccount> {
    @Override
    public TmdbAccount createApiToTest() {
        return getTmdbApi().getAccount();
    }

    /**
     * Test {@link TmdbAccount#getDetails(Integer, String)} with an expected result.
     */
    @Test
    public void testGetAccount() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/account/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234?session_id=testSessionId";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Account account = getApiToTest().getDetails(1234, "testSessionId");
        assertNotNull(account);
        validateAbstractJsonMappingFields(account);
    }

    /**
     * Test {@link TmdbAccount#addFavorite(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddFavourite() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/favorite?session_id=testSessionId";
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("favorite", true);
        String jsonBody = JsonUtil.toJson(requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_favourite.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().addFavorite(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Test {@link TmdbAccount#removeFavorite(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFavorite() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/favorite?session_id=testSessionId";
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("favorite", false);
        String jsonBody = JsonUtil.toJson(requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_favourite.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().removeFavorite(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Test {@link TmdbAccount#addToWatchList(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddToWatchList() throws IOException, TmdbException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/watchlist?session_id=testSessionId";
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("watchlist", true);
        String jsonBody = JsonUtil.toJson(requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_to_watchlist.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().addToWatchList(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Test {@link TmdbAccount#removeFromWatchList(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFromWatchList() throws IOException, TmdbException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/watchlist?session_id=testSessionId";
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("watchlist", false);
        String jsonBody = JsonUtil.toJson(requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_to_watchlist.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().removeFromWatchList(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Test {@link TmdbAccount#getFavoriteMovies(Integer, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetFavouriteMovies() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/favorite/movies?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/favourite_movies.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = getApiToTest().getFavoriteMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(movieResultsPage);
        validateAbstractJsonMappingFields(movieResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getFavoriteTv(Integer, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetFavouriteTv() throws IOException, TmdbException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/favorite/tv?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/favourite_tv.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getFavoriteTv(accountId, sessionId, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getLists(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer page = 1;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/lists?session_id=testSessionId&page=1";
        String body = TestUtils.readTestFile("api_responses/account/lists.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieListResultsPage movieListResultsPage = getApiToTest().getLists(accountId, sessionId, page);
        assertNotNull(movieListResultsPage);
        validateAbstractJsonMappingFields(movieListResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getRatedMovies(int, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedMovies() throws TmdbException, IOException {
        int accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/rated/movies?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/rated_movies.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RatedMovieResultsPage ratedMovieResultsPage = getApiToTest().getRatedMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedMovieResultsPage);
        validateAbstractJsonMappingFields(ratedMovieResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getRatedTvSeries(int, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvSeries() throws TmdbException, IOException {
        int accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/rated/tv?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/rated_tv.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RatedTvSeriesResultsPage ratedTvSeriesResultsPage = getApiToTest().getRatedTvSeries(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedTvSeriesResultsPage);
        validateAbstractJsonMappingFields(ratedTvSeriesResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getRatedTvEpisodes(int, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvEpisodes() throws TmdbException, IOException {
        int accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/rated/tv/episodes?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/rated_tv_episodes.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RatedTvEpisodeResultsPage ratedTvEpisodesResultsPage =
            getApiToTest().getRatedTvEpisodes(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedTvEpisodesResultsPage);
        validateAbstractJsonMappingFields(ratedTvEpisodesResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getWatchListMovies(Integer, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetWatchListMovies() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/watchlist/movies?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/watchlist_movies.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = getApiToTest().getWatchListMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(movieResultsPage);
        validateAbstractJsonMappingFields(movieResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getWatchListTvSeries(Integer, String, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetWatchListTvSeries() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        AccountSortBy sortBy = AccountSortBy.CREATED_AT_ASC;

        String url = TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/watchlist/tv?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc";
        String body = TestUtils.readTestFile("api_responses/account/watchlist_tv.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getWatchListTvSeries(accountId, sessionId, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }
}
