package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.TokenAuthorisation;
import info.movito.themoviedbapi.model.config.TokenSession;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AuthApiTest extends AbstractTmdbApiTest {


    @Test
    public void testGetAuthorisationToken() throws Exception {

        TokenAuthorisation result = tmdb.getAuthentication().getAuthorisationToken();
        assertFalse("Token is null", result == null);
        assertTrue("Token is not valid", result.getSuccess());

    }


    /**
     * Test of getSessionToken method, of class TmdbApi.
     * <p/>
     * TODO: Cannot be tested without a HTTP authorisation: http://help.themoviedb.org/kb/api/user-authentication
     */
    public void testGetSessionToken() throws Exception {

        TokenAuthorisation token = tmdb.getAuthentication().getAuthorisationToken();
        assertFalse("Token is null", token == null);
        assertTrue("Token is not valid", token.getSuccess());


        TokenSession result = tmdb.getAuthentication().getSessionToken(token);
        assertFalse("Session token is null", result == null);
        assertTrue("Session token is not valid", result.getSuccess());

    }


    @Ignore("Not ready yet")
    public void testGetGuestSessionToken() throws Exception {

        TokenSession result = tmdb.getAuthentication().getGuestSessionToken();

        assertTrue("Failed to get guest session", result.getSuccess());
    }


}
