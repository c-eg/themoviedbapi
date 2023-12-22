package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.authentication.GuestSession;
import info.movito.themoviedbapi.model.authentication.RequestToken;
import info.movito.themoviedbapi.model.authentication.Session;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusAuthentication;
import info.movito.themoviedbapi.model.core.responses.ResponseStatusDelete;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.Utils;

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
     * <p>Creates a guest session.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-create-guest-session">documentation</a>
     * for more info.</p>
     *
     * @return The guest session.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public GuestSession createGuestSession() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "guest_session/new");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, GuestSession.class);
    }

    /**
     * <p>Creates an unauthenticated request token. This will need to be authenticated by the user using
     * {@link #getTmdbAuthenticationUrlForRequestToken(RequestToken, String)}. If you cannot redirect the user to a browser,
     * use {@link #createAuthenticatedRequestToken(RequestToken, String, String)} instead.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-create-request-token">documentation</a>
     * for more info.</p>
     *
     * @return The unauthenticated request token.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RequestToken createRequestToken() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "token/new");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, RequestToken.class);
    }

    /**
     * <p>Creates the url to redirect the user to in order to authenticate their request token.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-how-do-i-generate-a-session-id">documentation</a>
     * for more info.</p>
     *
     * @param token The request token.
     * @param redirectUrl optional - The url to redirect the user to after authentication.
     * @return The url to redirect the user to.
     * @throws TmdbException If the request token is null or not successful.
     */
    public static String getTmdbAuthenticationUrlForRequestToken(RequestToken token, String redirectUrl) throws TmdbException {
        if (token == null || !token.getSuccess()) {
            throw new TmdbException("Invalid request token! The request token must not be null and must be successful!");
        }

        StringBuilder sb = new StringBuilder("https://www.themoviedb.org/authenticate/");
        sb.append(token.getRequestToken());

        if (redirectUrl != null && !redirectUrl.trim().isEmpty()) {
            sb.append("?redirect_to=").append(redirectUrl);
        }

        return sb.toString();
    }

    /**
     * <p>Creates an authenticated request token, with the non-authenticated request token, username and password.</p>
     * <p>Use this function if you have no way of directing the user to a browser for authentication.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-create-session-from-login">documentation</a>
     * for more info.</p>
     *
     * @param token The unauthenticated request token.
     * @param username The TMDB account username.
     * @param password The TMDB account password.
     * @return The authenticated request token.
     * @throws TmdbException If the request token is null or not successful. Or, if there was an error making the request or mapping the
     * response.
     */
    public RequestToken createAuthenticatedRequestToken(RequestToken token, String username, String password) throws TmdbException {
        if (token == null || !token.getSuccess()) {
            throw new TmdbException("Invalid request token! The request token must not be null and must be successful!");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "token/validate_with_login");

        HashMap<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        body.put(PARAM_REQUEST_TOKEN, token.getRequestToken());

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, RequestToken.class);
    }

    /**
     * <p>Creates a session with an authenticated request token.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-create-session">documentation</a>
     * for more info.</p>
     *
     * @param token The authenticated request token.
     * @return The session.
     * @throws TmdbException If the request token is null or not successful.
     */
    public Session createSession(RequestToken token) throws TmdbException {
        if (token == null || !token.getSuccess()) {
            throw new TmdbException("Invalid request token! The request token must not be null and must be successful!");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "session/new");

        HashMap<String, Object> body = new HashMap<>();
        body.put(PARAM_REQUEST_TOKEN, token.getRequestToken());

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, Session.class);
    }

    /**
     * <p>Deletes a session.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-delete-session">documentation</a>
     * for more info.</p>
     *
     * @param sessionId The session id.
     * @return The response status.
     * @throws TmdbException If the session id is null or empty. Or, if there was an error making the request or mapping the response.
     */
    public ResponseStatusDelete deleteSession(String sessionId) throws TmdbException {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            throw new TmdbException("Invalid session id! The session id must not be null or empty!");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH, "session");

        HashMap<String, Object> body = new HashMap<>();
        body.put("session_id", sessionId);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makeDeleteRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, ResponseStatusDelete.class);
    }

    /**
     * <p>Test your API Key to see if it's valid.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/authentication-validate-key">documentation</a>
     * for more info.</p>
     *
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatusAuthentication validateKey() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_AUTH);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ResponseStatusAuthentication.class);
    }
}
