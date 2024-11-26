package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;

import info.movito.themoviedbapi.model.core.Genre;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbGenre.TMDB_METHOD_GENRE;
import static info.movito.themoviedbapi.testutil.TestUtils.validateAbstractJsonMappingFields;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbGenre}.
 */
public class TmdbGenresTest extends AbstractTmdbApiTest<TmdbGenre> {
    @Override
    public TmdbGenre createApiToTest() {
        return getTmdbApi().getGenre();
    }

    /**
     * Tests {@link TmdbGenre#getMovieList(String)} with an expected result.
     */
    @Test
    public void testGetMovieList() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/genres/movie_list.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_GENRE + "/movie/list?language=en";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        List<Genre> genres = getApiToTest().getMovieList("en");
        assertNotNull(genres);
        assertFalse(genres.isEmpty());

        Genre genre = genres.get(0);
        assertNotNull(genre);
        validateAbstractJsonMappingFields(genre);
    }

    /**
     * Tests {@link TmdbGenre#getTvList(String)} with an expected result.
     */
    @Test
    public void testGetTvList() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/genres/tv_list.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_GENRE + "/tv/list?language=en";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        List<Genre> genres = getApiToTest().getTvList("en");
        assertNotNull(genres);
        assertFalse(genres.isEmpty());

        Genre genre = genres.get(0);
        assertNotNull(genre);
        validateAbstractJsonMappingFields(genre);
    }
}
