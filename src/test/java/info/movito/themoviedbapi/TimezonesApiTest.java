package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.Timezone;

import org.junit.Test;

import java.util.List;
import java.util.TimeZone;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;

public class TimezonesApiTest extends AbstractTmdbApiTest {

    @Test
    public void testGetAuthorisationToken() throws Exception {
        List<Timezone> tzlist = tmdb.getTimezones();
        TreeSet<String> tzset = new TreeSet();
        
        for (Timezone tz : tzlist) {
            // Check if the time zone conforms to the JAVA TimeZone names 
            TimeZone t = TimeZone.getTimeZone(tz.getName());
            assertFalse("Timezone '" + tz + "' is unknown", t == null);
            // Check If it is unique
            assertFalse("Timezone '" + tz.getName() + "' already defined", tzlist.contains(tz.getName()));
            tzset.add(tz.getName());
        }
    }
}
