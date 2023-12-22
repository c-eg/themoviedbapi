package info.movito.themoviedbapi;

import org.junit.jupiter.api.Test;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.model.authentication.GuestSession;
import info.movito.themoviedbapi.model.authentication.RequestToken;
import info.movito.themoviedbapi.model.authentication.Session;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusAuthentication;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusDelete;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.TmdbException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static info.movito.themoviedbapi.tools.TmdbResponseCode.INVALID_API_KEY;

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
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        GuestSession guestSession = tmdbAuthentication.createGuestSession();
        assertNotNull(guestSession);
        testForNullFieldsAndUnknownProperties(guestSession);
    }

    /**
     * Tests {@link TmdbAuthentication#createRequestToken()} with an expected result.
     */
    @Test
    public void testCreateRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = tmdbAuthentication.createRequestToken();
        assertNotNull(requestToken);
        testForNullFieldsAndUnknownProperties(requestToken);
    }

    /**
     * Tests {@link TmdbAuthentication#getTmdbAuthenticationUrlForRequestToken(RequestToken, String)} with an expected result.
     */
    @Test
    public void testGetTmdbAuthenticationUrlForRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = tmdbAuthentication.createRequestToken();
        String url = TmdbAuthentication.getTmdbAuthenticationUrlForRequestToken(requestToken, "redirectUrl");
        assertEquals("https://www.themoviedb.org/authenticate/ff5c7eeb5a8870efe3cd7fc5c282cffd26800ecd?redirect_to=redirectUrl", url);
    }

    /**
     * Tests {@link TmdbAuthentication#getTmdbAuthenticationUrlForRequestToken(RequestToken, String)} with a request token that is not
     * successful.
     */
    @Test
    public void testGetTmdbAuthenticationUrlForRequestTokenUnccessfulRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        mockResponse(body, 200);

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
        String body = TestUtils.readTestFile("api_responses/authentication/create_session.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        Session session = tmdbAuthentication.createSession(requestToken);
        assertNotNull(session);
        testForNullFieldsAndUnknownProperties(session);
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
    public void testCreateSessionWithLogin() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_session_with_login.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);

        RequestToken authenticatedRequestToken = tmdbAuthentication.createAuthenticatedRequestToken(requestToken, "username", "password");
        assertNotNull(authenticatedRequestToken);
        testForNullFieldsAndUnknownProperties(authenticatedRequestToken);
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
        String body = TestUtils.readTestFile("api_responses/authentication/delete_session.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        ResponseStatusDelete responseStatusDelete = tmdbAuthentication.deleteSession("sessionId");
        assertNotNull(responseStatusDelete);
        testForNullFieldsAndUnknownProperties(responseStatusDelete);

        assertTrue(responseStatusDelete.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#deleteSession(String)} with a null session_id.
     */
    @Test
    public void testDeleteSessionNullSession() throws IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/delete_session.json");
        mockResponse(body, 200);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        assertThrows(TmdbException.class, () -> tmdbAuthentication.deleteSession(null));
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an expected result.
     */
    @Test
    public void testValidateKey() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/validate_key.json");
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
        String body = TestUtils.readTestFile("api_responses/authentication/validate_key_unsuccessful.json");
        mockResponse(body, 401);

        TmdbAuthentication tmdbAuthentication = getTmdbApi().getAuthentication();
        TmdbResponseException exception = assertThrowsExactly(TmdbResponseException.class, tmdbAuthentication::validateKey);
        assertEquals(INVALID_API_KEY, exception.getResponseCode());
    }
}
