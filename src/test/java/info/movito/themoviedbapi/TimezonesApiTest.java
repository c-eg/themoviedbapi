package info.movito.themoviedbapi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.TimeZone;

import info.movito.themoviedbapi.model.config.TmdbTimezones;
import info.movito.themoviedbapi.model.config.TokenAuthorisation;

import java.util.TimeZone;

import org.junit.Test;

public class TimezonesApiTest extends AbstractTmdbApiTest {
    @Test
    public void testGetAuthorisationToken() throws Exception {

        TmdbTimezones tz = tmdb.getTimezones();
        assertFalse("Timezones are null", tz == null);

        for(String id : tz) {
        	TimeZone t = TimeZone.getTimeZone(id);
            assertFalse("Timezone '"+id+"' is unknown", t == null);
        }
    }
}
