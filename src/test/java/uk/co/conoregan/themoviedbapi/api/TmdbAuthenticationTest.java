package uk.co.conoregan.themoviedbapi.api;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.TestUtils;
import uk.co.conoregan.themoviedbapi.model.authentication.GuestSession;
import uk.co.conoregan.themoviedbapi.model.authentication.RequestToken;
import uk.co.conoregan.themoviedbapi.model.authentication.Session;
import uk.co.conoregan.themoviedbapi.model.authentication.SessionLogin;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatusAuthentication;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatusDelete;
import uk.co.conoregan.themoviedbapi.model.core.responses.TmdbResponseException;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.co.conoregan.themoviedbapi.tools.TmdbResponseCode.INVALID_API_KEY;

/**
 * Tests for {@link uk.co.conoregan.themoviedbapi.api.TmdbAuthentication}.
 */
public class TmdbAuthenticationTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbAuthentication#createGuestSession()} with an expected result.
     */
    @Test
    public void testCreateGuestSession() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("authentication/create_guest_session.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        GuestSession guestSession = tmdbAuthentication.createGuestSession();
        assertNotNull(guestSession);
        testForNullFieldsAndUnknownProperties(guestSession);

        assertEquals("2016-08-27 16:26:40 UTC", guestSession.getExpiresAt());
        assertEquals("1ce82ec1223641636ad4a60b07de3581", guestSession.getGuestSessionId());
        assertTrue(guestSession.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#createRequestToken()} with an expected result.
     */
    @Test
    public void testCreateRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("authentication/create_request_token.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = tmdbAuthentication.createRequestToken();
        assertNotNull(requestToken);
        testForNullFieldsAndUnknownProperties(requestToken);

        assertEquals("2016-08-26 17:04:39 UTC", requestToken.getExpires());
        assertEquals("ff5c7eeb5a8870efe3cd7fc5c282cffd26800ecd", requestToken.getRequestToken());
        assertTrue(requestToken.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#createSession(RequestToken)} with an expected result.
     */
    @Test
    public void testCreateSession() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("authentication/create_session.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        Session session = tmdbAuthentication.createSession(requestToken);
        assertNotNull(session);
        testForNullFieldsAndUnknownProperties(session);

        assertEquals("79191836ddaa0da3df76a5ffef6f07ad6ab0c641", session.getSessionId());
        assertTrue(session.getSuccess());
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
     * Tests {@link TmdbAuthentication#createSession(RequestToken, String, String)} with an expected result.
     */
    @Test
    public void testCreateSessionWithLogin() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("authentication/create_session_with_login.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        SessionLogin sessionLogin = tmdbAuthentication.createSession(requestToken, "username", "password");
        assertNotNull(sessionLogin);
        testForNullFieldsAndUnknownProperties(sessionLogin);

        assertEquals("2018-07-24 04:10:26 UTC", sessionLogin.getExpiresAt());
        assertEquals("1531f1a558c8357ce8990cf887ff196e8f5402ec", sessionLogin.getRequestToken());
        assertTrue(sessionLogin.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#createSession(RequestToken, String, String)} with a request token that is not successful.
     */
    @Test
    public void testCreateSessionWithLoginUnsuccessfulRequestToken() {
        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(false);
        assertThrows(TmdbException.class, () -> tmdbAuthentication.createSession(requestToken, "username", "password"));
    }

    /**
     * Tests {@link TmdbAuthentication#getSessionLogin(String, String)} with an expected result.
     */
    @Test
    public void testGetSessionLogin() throws TmdbException, IOException {
        String requestTokenBody = TestUtils.readTestFile("authentication/create_request_token.json");
        mockResponse(requestTokenBody, 200);
        String sessionWithLoginBody = TestUtils.readTestFile("authentication/create_session_with_login.json");
        mockResponse(sessionWithLoginBody, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        SessionLogin sessionLogin = tmdbAuthentication.getSessionLogin("username", "password");
        assertNotNull(sessionLogin);
        testForNullFieldsAndUnknownProperties(sessionLogin);
        assertEquals(2, getServer().getRequestCount());

        assertEquals("2018-07-24 04:10:26 UTC", sessionLogin.getExpiresAt());
        assertEquals("1531f1a558c8357ce8990cf887ff196e8f5402ec", sessionLogin.getRequestToken());
        assertTrue(sessionLogin.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#getSessionLogin(String, String)} with a request token that is not successful.
     */
    @Test
    public void testGetSessionLoginUnsuccessfulRequestToken() throws IOException {
        String requestTokenBody = TestUtils.readTestFile("authentication/create_request_token_unsuccessful.json");
        mockResponse(requestTokenBody, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        assertThrows(TmdbException.class, () -> tmdbAuthentication.getSessionLogin("username", "password"));
    }

    /**
     * Tests {@link TmdbAuthentication#getSessionLogin(String, String)} with a session login that is not successful.
     */
    @Test
    public void testGetSessionLoginUnsuccessfulSessionLogin() throws IOException {
        String requestTokenBody = TestUtils.readTestFile("authentication/create_request_token.json");
        mockResponse(requestTokenBody, 200);
        String sessionWithLoginBody = TestUtils.readTestFile("authentication/create_session_with_login_unsuccessful.json");
        mockResponse(sessionWithLoginBody, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        assertThrows(TmdbException.class, () -> tmdbAuthentication.getSessionLogin("username", "password"));
        assertEquals(2, getServer().getRequestCount());
    }

    /**
     * Tests {@link TmdbAuthentication#deleteSession(String)} with an expected result.
     */
    @Test
    public void testDeleteSession() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("authentication/delete_session.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        ResponseStatusDelete responseStatusDelete = tmdbAuthentication.deleteSession("sessionId");
        assertNotNull(responseStatusDelete);
        testForNullFieldsAndUnknownProperties(responseStatusDelete);

        assertTrue(responseStatusDelete.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an expected result.
     */
    @Test
    public void testValidateKey() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("authentication/validate_key.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        ResponseStatusAuthentication responseStatusAuthentication = tmdbAuthentication.validateKey();
        assertNotNull(responseStatusAuthentication);
        testForNullFieldsAndUnknownProperties(responseStatusAuthentication);

        assertTrue(responseStatusAuthentication.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an unsuccessful result.
     */
    @Test
    public void testValidateKeyUnsuccessful() throws IOException {
        String body = TestUtils.readTestFile("authentication/validate_key_unsuccessful.json");
        mockResponse(body, 401);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        TmdbResponseException exception = assertThrowsExactly(TmdbResponseException.class, tmdbAuthentication::validateKey);
        assertEquals(INVALID_API_KEY, exception.getResponseCode());
    }
}
