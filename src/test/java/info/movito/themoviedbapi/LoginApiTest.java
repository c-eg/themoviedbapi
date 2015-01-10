package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.TokenAuthorisation;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.tools.MovieDbException;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

/**
 * New tests for user/password authorization.
 * Will be skipped if environment variables
 * 'username' and 'password' are not set.
 *
 * @author kloska
 */

public class LoginApiTest extends AbstractTmdbApiTest {

    @BeforeClass
    public static void checkEnvironment() {
        String userName = System.getenv("username");
        String userPassword = System.getenv("password");
        assumeFalse("test skipped. set env variables 'username' and 'password' to test login",
                StringUtils.isBlank(userName) || StringUtils.isBlank(userPassword));
    }

    @Test
    public void testGetLoginAuthorisation() throws Exception {

        String userName = System.getenv("username");
        String userPassword = System.getenv("password");
        TokenAuthorisation token_in = tmdb.getAuthentication().getAuthorisationToken();

        assertFalse("Token_in is null", token_in == null);
        assertTrue("Token_in is not valid", token_in.getSuccess());

        TokenAuthorisation token_out = tmdb.getAuthentication().getLoginToken(token_in, userName, userPassword);
        assertFalse("Token_out is null", token_out == null);
        assertTrue("Token_out is not valid", token_out.getSuccess());
    }

    @Test
    public void testLogin() throws Exception {
        String userName = System.getenv("username");
        String userPassword = System.getenv("password");

        TokenSession token_session = tmdb.getAuthentication().getSessionLogin(userName, userPassword);
        assertFalse("Token session is null", token_session == null);
        assertTrue("Token_session is not valid:", token_session.getSuccess());
    }

    @Test(expected = MovieDbException.class)
    public void testInvalidLogin() throws Exception {
        String userName = "apitests";
        String userPassword = "wrongpw";

        TokenSession token_session = tmdb.getAuthentication().getSessionLogin(userName, userPassword);
        assertFalse("Token session is null", token_session == null);
        assertTrue("Token_session is not valid:", token_session.getSuccess());
    }
}
