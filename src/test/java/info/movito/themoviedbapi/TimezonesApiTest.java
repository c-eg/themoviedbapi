package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.Timezone;
import org.junit.Test;

import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertFalse;

public class TimezonesApiTest extends AbstractTmdbApiTest {

    @Test
    public void testGetAuthorisationToken() throws Exception {
        List<Timezone> tz = tmdb.getTimezones();

        for (Timezone id : tz) {
            TimeZone t = TimeZone.getTimeZone(id.getName());
            assertFalse("Timezone '" + id + "' is unknown", t == null);
        }


    }
}
