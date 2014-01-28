package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.TokenAuthorisation;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;


public class TmdbAuthentication extends AbstractApiElement {

    public static final String PARAM_TOKEN = "request_token";
    public static final String TMDB_METHOD_AUTH = "authentication";


    TmdbAuthentication(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * This method is used to generate a valid request token for user based authentication.
     * <p/>
     * A request token is required in order to request a session id.
     * <p/>
     * You can generate any number of request tokens but they will expire after 60 minutes.
     * <p/>
     * As soon as a valid session id has been created the token will be destroyed.
     */
    public TokenAuthorisation getAuthorisationToken() {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_AUTH, "token/new");

        return mapJsonResult(apiUrl, TokenAuthorisation.class);
    }


    /**
     * This method is used to generate a session id for user based authentication.
     * <p/>
     * A session id is required in order to use any of the write methods.
     *
     * @param token
     */
    public TokenSession getSessionToken(TokenAuthorisation token) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_AUTH, "session/new");

        if (!token.getSuccess()) {
            logger.warn("Authorisation token was not successful!");
            throw new MovieDbException(MovieDbException.MovieDbExceptionType.AUTHORISATION_FAILURE, "Authorisation token was not successful!");
        }

        apiUrl.addParam(PARAM_TOKEN, token.getRequestToken());

        return mapJsonResult(apiUrl, TokenSession.class);
    }


    /**
     * This method is used to generate a guest session id.
     * <p/>
     * A guest session can be used to rate movies without having a registered TMDb user account.
     * <p/>
     * You should only generate a single guest session per user (or device) as you will be able to attach the ratings to a TMDb user
     * account in the future.
     * <p/>
     * There are also IP limits in place so you should always make sure it's the end user doing the guest session actions.
     * <p/>
     * If a guest session is not used for the first time within 24 hours, it will be automatically discarded.
     */
    public TokenSession getGuestSessionToken() {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_AUTH, "guest_session/new");


        return mapJsonResult(apiUrl, TokenSession.class);
    }
}
