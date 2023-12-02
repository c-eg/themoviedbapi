package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.FindResults;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindApiTest extends AbstractTmdbApiTest {

    @Test
    public void testFindImdb() {
        FindResults result = tmdb.getFind().find("tt0120338", TmdbFind.ExternalSource.imdb_id, null);
        assertEquals("No genre movies found", 1, result.getMovieResults().size());
        assertEquals("Titanic not in results list", 597, result.getMovieResults().get(0).getId());
    }
}
