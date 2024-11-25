package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;

import info.movito.themoviedbapi.model.authentication.GuestSession;
import info.movito.themoviedbapi.model.authentication.RequestToken;
import info.movito.themoviedbapi.model.authentication.Session;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusAuthentication;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusDelete;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbAuthentication.TMDB_METHOD_AUTH;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.tools.TmdbResponseCode.INVALID_API_KEY;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbAuthentication}.
 */
public class TmdbAuthenticationTest extends AbstractTmdbApiTest<TmdbAuthentication> {
    @Override
    public TmdbAuthentication createApiToTest() {
        return getTmdbApi().getAuthentication();
    }

    /**
     * Tests {@link TmdbAuthentication#createGuestSession()} with an expected result.
     */
    @Test
    public void testCreateGuestSession() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_guest_session.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/guest_session/new";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        GuestSession guestSession = getApiToTest().createGuestSession();
        assertNotNull(guestSession);
        validateAbstractJsonMappingFields(guestSession);
    }

    /**
     * Tests {@link TmdbAuthentication#createRequestToken()} with an expected result.
     */
    @Test
    public void testCreateRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/new";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RequestToken requestToken = getApiToTest().createRequestToken();
        assertNotNull(requestToken);
        validateAbstractJsonMappingFields(requestToken);
    }

    /**
     * Tests {@link TmdbAuthentication#getTmdbAuthenticationUrlForRequestToken(RequestToken, String)} with an expected result.
     */
    @Test
    public void testGetTmdbAuthenticationUrlForRequestToken() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/authentication/create_request_token.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/new";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RequestToken requestToken = getApiToTest().createRequestToken();
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
        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/new";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RequestToken requestToken = getApiToTest().createRequestToken();
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
        String jsonBody = JsonUtil.convertToJson(AbstractTmdbApi.getObjectMapper(), requestBody);

        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/session/new";
        String body = TestUtils.readTestFile("api_responses/authentication/create_session.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        requestToken.setRequestToken(requestTokenStr);
        Session session = getApiToTest().createSession(requestToken);
        assertNotNull(session);
        validateAbstractJsonMappingFields(session);
    }

    /**
     * Tests {@link TmdbAuthentication#createSession(RequestToken)} with a request token that is not successful.
     */
    @Test
    public void testCreateSessionUnsuccessfulRequestToken() {
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(false);
        assertThrows(TmdbException.class, () -> getApiToTest().createSession(requestToken));
    }

    /**
     * Tests {@link TmdbAuthentication#createAuthenticatedRequestToken(RequestToken, String, String)} with an expected result.
     */
    @Test
    public void testCreateAuthenticatedRequestToken() throws TmdbException, IOException {
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(true);
        requestToken.setRequestToken("requestToken");

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", "username");
        requestBody.put("password", "password");
        requestBody.put("request_token", requestToken.getRequestToken());
        String jsonBody = JsonUtil.convertToJson(AbstractTmdbApi.getObjectMapper(), requestBody);

        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/token/validate_with_login";
        String body = TestUtils.readTestFile("api_responses/authentication/create_session_with_login.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        RequestToken authenticatedRequestToken = getApiToTest().createAuthenticatedRequestToken(requestToken, "username", "password");
        assertNotNull(authenticatedRequestToken);
        validateAbstractJsonMappingFields(authenticatedRequestToken);
    }

    /**
     * Tests {@link TmdbAuthentication#createAuthenticatedRequestToken(RequestToken, String, String)} with a request token that is not
     * successful.
     */
    @Test
    public void testCreateSessionWithLoginUnsuccessfulRequestToken() {
        RequestToken requestToken = new RequestToken();
        requestToken.setSuccess(false);
        assertThrows(TmdbException.class, () ->
            getApiToTest().createAuthenticatedRequestToken(requestToken, "username", "password"));
    }

    /**
     * Tests {@link TmdbAuthentication#deleteSession(String)} with an expected result.
     */
    @Test
    public void testDeleteSession() throws TmdbException, IOException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("session_id", "sessionId");
        String jsonBody = JsonUtil.convertToJson(AbstractTmdbApi.getObjectMapper(), requestBody);

        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH + "/session";
        String body = TestUtils.readTestFile("api_responses/authentication/delete_session.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.DELETE)).thenReturn(body);

        ResponseStatusDelete responseStatusDelete = getApiToTest().deleteSession("sessionId");
        assertNotNull(responseStatusDelete);
        validateAbstractJsonMappingFields(responseStatusDelete);

        assertTrue(responseStatusDelete.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#deleteSession(String)} with a null session_id.
     */
    @Test
    public void testDeleteSessionNullSession() {
        assertThrows(TmdbException.class, () -> getApiToTest().deleteSession(null));
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an expected result.
     */
    @Test
    public void testValidateKey() throws TmdbException, IOException {
        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH;
        String body = TestUtils.readTestFile("api_responses/authentication/validate_key.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ResponseStatusAuthentication responseStatusAuthentication = getApiToTest().validateKey();
        assertNotNull(responseStatusAuthentication);
        validateAbstractJsonMappingFields(responseStatusAuthentication);

        assertTrue(responseStatusAuthentication.getSuccess());
    }

    /**
     * Tests {@link TmdbAuthentication#validateKey()} with an unsuccessful result.
     */
    @Test
    public void testValidateKeyUnsuccessful() throws IOException, TmdbException {
        String url = TMDB_API_BASE_URL + TMDB_METHOD_AUTH;
        String body = TestUtils.readTestFile("api_responses/authentication/validate_key_unsuccessful.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbResponseException exception = assertThrowsExactly(TmdbResponseException.class, getApiToTest()::validateKey);
        assertEquals(INVALID_API_KEY, exception.getResponseCode());
    }
}
