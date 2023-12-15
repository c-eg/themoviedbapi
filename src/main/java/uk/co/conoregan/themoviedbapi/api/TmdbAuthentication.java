package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.authentication.GuestSession;
import uk.co.conoregan.themoviedbapi.model.authentication.RequestToken;
import uk.co.conoregan.themoviedbapi.model.authentication.Session;
import uk.co.conoregan.themoviedbapi.model.authentication.SessionLogin;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatusAuthentication;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatusDelete;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.util.Utils;

import java.util.HashMap;

/**
 * The movie database api for authentication. See the
 * <a href="https://developer.themoviedb.org/reference/authentication-how-do-i-generate-a-session-id">documentation</a> for more info.
 */
public class TmdbAuthentication extends AbstractTmdbApi {
    private static final String TMDB_METHOD_AUTH = "authentication";

    private static final String PARAM_REQUEST_TOKEN = "request_token";

    /**
     * Create a new TmdbAuthentication instance to call the authentication related TMDb API methods.
     */
    TmdbAuthentication(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Creates a guest session. Guest sessions will automatically be deleted if they are not used within 60 minutes of it being issued.
     * See the <a href="https://developer.themoviedb.org/reference/authentication-create-guest-session">documentation</a> for more info.
     */
    public GuestSession createGuestSession() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "guest_session/new");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, GuestSession.class);
    }

    /**
     * Creates a request token.
     * See the <a href="https://developer.themoviedb.org/reference/authentication-create-request-token">documentation</a> for more info.
     */
    public RequestToken createRequestToken() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "token/new");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, RequestToken.class);
    }

    /**
     * Creates a session.
     * See the <a href="https://developer.themoviedb.org/reference/authentication-create-session">documentation</a> for more info.
     */
    public Session createSession(RequestToken token) throws TmdbException {
        if (!token.getSuccess()) {
            throw new TmdbException("Authorisation token was not successful!");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "session/new");

        HashMap<String, Object> body = new HashMap<>();
        body.put(PARAM_REQUEST_TOKEN, token.getRequestToken());

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, Session.class);
    }

    /**
     * Creates a session, with the username and password.
     * <p>Use this function if you have no way of directing the user to a browser for authentication.</p>
     * See the <a href="https://developer.themoviedb.org/reference/authentication-create-session-from-login">documentation</a> for more
     * info.
     */
    public SessionLogin createSession(RequestToken token, String username, String password) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "token/validate_with_login");

        HashMap<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        body.put(PARAM_REQUEST_TOKEN, token.getRequestToken());

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, SessionLogin.class);
    }

    /**
     * Does all the necessary username/password authentication stuff in one go.
     * <p>Use this function if you have no way of directing the user to a browser for authentication.</p>
     *
     * @return validated TokenSession
     * @throws TmdbException if the login failed
     */
    public SessionLogin getSessionLogin(String username, String password) throws TmdbException {
        RequestToken requestToken = createRequestToken();
        if (!requestToken.getSuccess()) {
            throw new TmdbException("Authorisation token was not successful!");
        }

        SessionLogin session = createSession(requestToken, username, password);
        if (!session.getSuccess()) {
            throw new TmdbException("User authentication failed:" + session);
        }

        return session;
    }

    /**
     * Deletes a session.
     * See the <a href="https://developer.themoviedb.org/reference/authentication-delete-session">documentation</a> for more info.
     */
    public ResponseStatusDelete deleteSession(String sessionId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "session");

        HashMap<String, Object> body = new HashMap<>();
        body.put("session_id", sessionId);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makeDeleteRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, ResponseStatusDelete.class);
    }

    /**
     * Test your API Key to see if it's valid.
     * See the <a href="https://developer.themoviedb.org/reference/authentication-validate-key">documentation</a> for more info.
     */
    public ResponseStatusAuthentication validateKey() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ResponseStatusAuthentication.class);
    }
}
