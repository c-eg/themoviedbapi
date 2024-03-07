package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.authentication.GuestSession;
import info.movito.themoviedbapi.model.authentication.RequestToken;
import info.movito.themoviedbapi.model.authentication.Session;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusAuthentication;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusDelete;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static info.movito.themoviedbapi.AbstractTmdbApi.getObjectMapper;
import static info.movito.themoviedbapi.TmdbAuthentication.TMDB_METHOD_AUTH;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.tools.TmdbResponseCode.INVALID_API_KEY;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbAuthentication}.
 */
public class TmdbAuthenticationTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbAuthentication#createGuestSession()} with an expected result.
     */
    @Test
    public void testCreateGuestSession() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_guest_session.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/guest_session/new");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        GuestSession guestSession = tmdbAuthentication.createGuestSession();
        assertNotNull(guestSession);
        checkForNullAndEmptyFieldsAndNewItems(guestSession);
    }

    /**
     * Tests {@link TmdbAuthentication#createRequestToken()} with an expected result.
     */
    @Test
    public void testCreateRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/new");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = tmdbAuthentication.createRequestToken();
        assertNotNull(requestToken);
        checkForNullAndEmptyFieldsAndNewItems(requestToken);
    }

    /**
     * Tests {@link TmdbAuthentication#getTmdbAuthenticationUrlForRequestToken(RequestToken, String)} with an expected result.
     */
    @Test
    public void testGetTmdbAuthenticationUrlForRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/new");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = tmdbAuthentication.createRequestToken();
        String authUrl = TmdbAuthentication.getTmdbAuthenticationUrlForRequestToken(requestToken, "redirectUrl");
        assertEquals("https://www.themoviedb.org/authenticate/ff5c7eeb5a8870efe3cd7fc5c282cffd26800ecd?redirect_to=redirectUrl", authUrl);
    }

    /**
     * Tests {@link TmdbAuthentication#getTmdbAuthenticationUrlForRequestToken(RequestToken, String)} with a request token that is not
     * successful.
     */
    @Test
    public void testGetTmdbAuthenticationUrlForRequestTokenUnccessfulRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/new");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = tmdbAuthentication.createRequestToken();
        requestToken.setSuccess(false);

        assertThrows(TmdbException.class, () ->
            TmdbAuthentication.getTmdbAuthenticationUrlForRequestToken(requestToken, "redirectUrl"));
    }

    /**
     * Tests {@link TmdbAuthentication#createSession(RequestToken)} with an expected result.
     */
    @Test
    public void testCreateSession() throws TmdbException, IOException {
        String requestTokenStr = "requestToken";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("request_token", requestTokenStr);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/session/new");
        String body = TestUtils.readTestFile("api_responses/authentication/create_session.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        requestToken.setRequestToken(requestTokenStr);
        Session session = tmdbAuthentication.createSession(requestToken);
        assertNotNull(session);
        checkForNullAndEmptyFieldsAndNewItems(session);
    }

    /**
     * Tests {@link TmdbAuthentication#createSession(RequestToken)} with a request token that is not successful.
     */
    @Test
    public void testCreateSessionUnsuccessfulRequestToken() {
        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(false);
        assertThrows(TmdbException.class, () -> tmdbAuthentication.createSession(requestToken));
    }

    /**
     * Tests {@link TmdbAuthentication#createAuthenticatedRequestToken(RequestToken, String, String)} with an expected result.
     */
    @Test
    public void testCreateAuthenticatedRequestToken() throws TmdbException, IOException {
        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        requestToken.setRequestToken("requestToken");

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", "username");
        requestBody.put("password", "password");
        requestBody.put("request_token", requestToken.getRequestToken());
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/validate_with_login");
        String body = TestUtils.readTestFile("api_responses/authentication/create_session_with_login.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        RequestToken authenticatedRequestToken = tmdbAuthentication.createAuthenticatedRequestToken(requestToken, "username", "password");
        assertNotNull(authenticatedRequestToken);
        checkForNullAndEmptyFieldsAndNewItems(authenticatedRequestToken);
    }

    /**
     * Tests {@link TmdbAuthentication#createAuthenticatedRequestToken(RequestToken, String, String)} with a request token that is not
     * successful.
     */
    @Test
    public void testCreateSessionWithLoginUnsuccessfulRequestToken() {
        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(false);
        assertThrows(TmdbException.class, () ->
            tmdbAuthentication.createAuthenticatedRequestToken(requestToken, "username", "password"));
    }

    /**
     * Tests {@link TmdbAuthentication#deleteSession(String)} with an expected result.
     */
    @Test
    public void testDeleteSession() throws TmdbException, IOException {
        String sessionId = "sessionId";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("session_id", sessionId);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/session");
        String body = TestUtils.readTestFile("api_responses/authentication/delete_session.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.DELETE)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        ResponseStatusDelete responseStatusDelete = tmdbAuthentication.deleteSession("sessionId");
        assertNotNull(responseStatusDelete);
        checkForNullAndEmptyFieldsAndNewItems(responseStatusDelete);

        assertTrue(responseStatusDelete.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#deleteSession(String)} with a null session_id.
     */
    @Test
    public void testDeleteSessionNullSession() {
        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        assertThrows(TmdbException.class, () -> tmdbAuthentication.deleteSession(null));
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an expected result.
     */
    @Test
    public void testValidateKey() throws TmdbException, IOException {
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH);
        String body = TestUtils.readTestFile("api_responses/authentication/validate_key.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        ResponseStatusAuthentication responseStatusAuthentication = tmdbAuthentication.validateKey();
        assertNotNull(responseStatusAuthentication);
        checkForNullAndEmptyFieldsAndNewItems(responseStatusAuthentication);

        assertTrue(responseStatusAuthentication.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an unsuccessful result.
     */
    @Test
    public void testValidateKeyUnsuccessful() throws IOException, TmdbException {
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_AUTH);
        String body = TestUtils.readTestFile("api_responses/authentication/validate_key_unsuccessful.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        TmdbResponseException exception = assertThrowsExactly(TmdbResponseException.class, tmdbAuthentication::validateKey);
        assertEquals(INVALID_API_KEY, exception.getResponseCode());
    }
}
