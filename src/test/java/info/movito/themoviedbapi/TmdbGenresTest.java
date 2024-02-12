package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbGenre.TMDB_METHOD_GENRE;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbGenre}.
 */
public class TmdbGenresTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbGenre#getMovieList(String)} with an expected result.
     */
    @Test
    public void testGetMovieList() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/genres/movie_list.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_GENRE + "/movie/list?language=en");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbGenre tmdbGenre = new TmdbGenre(getTmdbApi());
        List<Genre> genres = tmdbGenre.getMovieList("en");
        assertNotNull(genres);
        assertFalse(genres.isEmpty());

        Genre genre = genres.get(0);
        assertNotNull(genre);
        testForNullFieldsAndNewItems(genre);
    }

    /**
     * Tests {@link TmdbGenre#getTvList(String)} with an expected result.
     */
    @Test
    public void testGetTvList() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/genres/tv_list.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_GENRE + "/tv/list?language=en");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbGenre tmdbGenre = new TmdbGenre(getTmdbApi());
        List<Genre> genres = tmdbGenre.getTvList("en");
        assertNotNull(genres);
        assertFalse(genres.isEmpty());

        Genre genre = genres.get(0);
        assertNotNull(genre);
        testForNullFieldsAndNewItems(genre);
    }
}
