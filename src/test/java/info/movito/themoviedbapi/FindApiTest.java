package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.FindResults;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class FindApiTest extends AbstractTmdbApiTest {


    @Test
    public void testFindImdb() {
        FindResults result = tmdb.getFind().find("tt0120338", TmdbFind.ExternalSource.imdb_id, null);
        assertTrue("No genre movies found", result.getMovieResults().size() == 1);
        assertTrue("Titanic not in results list", result.getMovieResults().get(0).getId() == 597);
    }
}
