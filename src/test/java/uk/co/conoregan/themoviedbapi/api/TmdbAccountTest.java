package uk.co.conoregan.themoviedbapi.api;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.TestUtils;
import uk.co.conoregan.themoviedbapi.model.config.Account;
import uk.co.conoregan.themoviedbapi.model.core.AccountID;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.util.Utils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.co.conoregan.themoviedbapi.api.TmdbAccount.PARAM_SESSION;
import static uk.co.conoregan.themoviedbapi.api.TmdbAccount.TMDB_METHOD_ACCOUNT;

/**
 * Tests for {@link uk.co.conoregan.themoviedbapi.api.TmdbAccount}.
 */
public class TmdbAccountTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbAccount#getDetails(String)} with an expected result.
     */
    @Test
    public void testGetAccount() throws IOException, TmdbException {
        getServer().url(TMDB_METHOD_ACCOUNT);

        String body = TestUtils.readTestFile("account/details.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        Account account = tmdbAccount.getDetails("testSessionToken");
        assertNotNull(account);
        assertTrue(account.getUnknownProperties().isEmpty());  // check for no unknown properties

        assertEquals("c9e9fc152ee756a900db85757c29815d", account.getAvatar().getGravatarHash());
        assertEquals("/xy44UvpbTgzs9kWmp4C3fEaCl5h.png", account.getAvatar().getTmdbAvatarPath());
        assertEquals(548, account.getId());
        assertEquals("en", account.getIso6391());
        assertEquals("CA", account.getIso31661());
        assertEquals("Travis Bell", account.getName());
        assertFalse(account.isIncludeAdult());
        assertEquals("travisbell", account.getUsername());
    }

    /**
     * Test {@link TmdbAccount#addFavorite(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddFavourite() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "favorite");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        getServer().url(apiEndpoint.buildUrl());

        String body = TestUtils.readTestFile("account/add_favourite.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.addFavorite(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#removeFavorite(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFavorite() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "favorite");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        getServer().url(apiEndpoint.buildUrl());

        String body = TestUtils.readTestFile("account/add_favourite.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.removeFavorite(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#addToWatchList(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddToWatchList() throws IOException, TmdbException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "watchlist");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        getServer().url(apiEndpoint.buildUrl());

        String body = TestUtils.readTestFile("account/add_to_watchlist.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.addToWatchList(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#removeFromWatchList(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFromWatchList() throws IOException, TmdbException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "watchlist");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        getServer().url(apiEndpoint.buildUrl());

        String body = TestUtils.readTestFile("account/add_to_watchlist.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.removeFromWatchList(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    @Test
    public void testGetFavouriteMovies() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "watchlist");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        getServer().url(apiEndpoint.buildUrl());

        String body = TestUtils.readTestFile("account/favourite_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.removeFromWatchList(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }
}
