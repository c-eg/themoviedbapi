package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.account.Account;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.sortby.AccountSortBy;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static info.movito.themoviedbapi.AbstractTmdbApi.getObjectMapper;
import static info.movito.themoviedbapi.TmdbAccount.TMDB_METHOD_ACCOUNT;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbAccount}.
 */
public class TmdbAccountTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbAccount#getDetails(Integer, String)} with an expected result.
     */
    @Test
    public void testGetAccount() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/account/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234?session_id=testSessionId");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        Account account = tmdbAccount.getDetails(1234, "testSessionId");
        assertNotNull(account);
        checkForNullAndEmptyFieldsAndNewItems(account);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/favorite?session_id=testSessionId");
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("favorite", true);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_favourite.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.addFavorite(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/favorite?session_id=testSessionId");
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("favorite", false);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_favourite.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.removeFavorite(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/watchlist?session_id=testSessionId");
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("watchlist", true);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_to_watchlist.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.addToWatchList(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/watchlist?session_id=testSessionId");
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_type", mediaType.toString());
        requestBody.put("media_id", mediaId);
        requestBody.put("watchlist", false);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        String body = TestUtils.readTestFile("api_responses/account/add_to_watchlist.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.removeFromWatchList(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/favorite/movies?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/favourite_movies.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        MovieResultsPage movieResultsPage = tmdbAccount.getFavoriteMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(movieResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(movieResultsPage);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/favorite/tv?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/favourite_tv.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        TvSeriesResultsPage tvSeriesResultsPage = tmdbAccount.getFavoriteTv(accountId, sessionId, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(tvSeriesResultsPage);
    }

    /**
     * Test {@link TmdbAccount#getLists(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer page = 1;

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT + "/1234/lists?session_id=testSessionId&page=1");
        String body = TestUtils.readTestFile("api_responses/account/lists.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        MovieListResultsPage movieListResultsPage = tmdbAccount.getLists(accountId, sessionId, page);
        assertNotNull(movieListResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(movieListResultsPage);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/rated/movies?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/rated_movies.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        RatedMovieResultsPage ratedMovieResultsPage = tmdbAccount.getRatedMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedMovieResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(ratedMovieResultsPage);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/rated/tv?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/rated_tv.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        RatedTvSeriesResultsPage ratedTvSeriesResultsPage = tmdbAccount.getRatedTvSeries(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedTvSeriesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(ratedTvSeriesResultsPage);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/rated/tv/episodes?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/rated_tv_episodes.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        RatedTvEpisodeResultsPage ratedTvEpisodesResultsPage =
            tmdbAccount.getRatedTvEpisodes(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedTvEpisodesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(ratedTvEpisodesResultsPage);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/watchlist/movies?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/watchlist_movies.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        MovieResultsPage movieResultsPage = tmdbAccount.getWatchListMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(movieResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(movieResultsPage);
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

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_ACCOUNT +
            "/1234/watchlist/tv?session_id=testSessionId&language=en&page=1&sort_by=created_at.asc");
        String body = TestUtils.readTestFile("api_responses/account/watchlist_tv.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        TvSeriesResultsPage tvSeriesResultsPage = tmdbAccount.getWatchListTvSeries(accountId, sessionId, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(tvSeriesResultsPage);
    }
}
