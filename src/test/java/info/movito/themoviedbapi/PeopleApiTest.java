package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCredit;
import info.movito.themoviedbapi.model.people.PersonCredits;
import info.movito.themoviedbapi.model.people.PersonPeople;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class PeopleApiTest extends AbstractTmdbApiTest {


    @Test
    public void testGetPersonInfo() {
        PersonPeople result = tmdb.getPeople().getPersonInfo(ID_PERSON_BRUCE_WILLIS);
        assertTrue("Wrong actor returned", result.getId() == ID_PERSON_BRUCE_WILLIS);
    }


    @Test
    public void testGetPersonCredits() {
        PersonCredits result = tmdb.getPeople().getCombinedPersonCredits(ID_PERSON_BRUCE_WILLIS);
        assertTrue("No cast information", result.getCast().size() > 0);
    }


    @Test
    public void testGetPersonImages() {
        List<Artwork> result = tmdb.getPeople().getPersonImages(ID_PERSON_BRUCE_WILLIS);
        assertTrue("No cast information", result.size() > 0);
    }

    @Test
    public void testGetPersonKnownFor() {
        PersonPeople result = tmdb.getPeople().getPersonInfo(ID_PERSON_BRUCE_WILLIS);
        assertEquals("Acting", result.getKnownForDepartment());
    }

    @Test
    public void testGetPersonLatest() throws Exception {
        PersonPeople result = tmdb.getPeople().getPersonLatest();

        assertNotNull("No results found", result);
        assertTrue("No results found", StringUtils.isNotBlank(result.getName()));
    }


    @Ignore("Not ready yet")
    public void testGetPersonChanges() throws Exception {

        String startDate = "";
        String endDate = "";
        tmdb.getPeople().getPersonChanges(ID_PERSON_BRUCE_WILLIS, startDate, endDate);
    }


    @Test
    public void testGetPersonPopular_int() throws Exception {

        int page = 0;
        List<Person> result = tmdb.getPeople().getPersonPopular(page).getResults();
        assertFalse("No popular people", result.isEmpty());
    }

}
