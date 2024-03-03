package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.movielists.Dates;
import info.movito.themoviedbapi.model.movielists.MovieResultsPageWithDates;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbMovieLists.TMDB_METHOD_MOVIE_LISTS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbMovieLists}.
 */
public class TmdbMovieListsTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbMovieLists#getNowPlaying(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetNowPlaying() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/now_playing.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/now_playing?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovieLists tmdbMovieLists = getTmdbApi().getMovieLists();
        MovieResultsPageWithDates movieResultsPageWithDates = tmdbMovieLists.getNowPlaying("en-US", 1, null);
        assertNotNull(movieResultsPageWithDates);
        testForNullFieldsAndNewItems(movieResultsPageWithDates);

        Dates dates = movieResultsPageWithDates.getDates();
        assertNotNull(dates);
        testForNullFieldsAndNewItems(dates);

        List<Movie> movies = movieResultsPageWithDates.getResults();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        Movie movie = movies.get(0);
        assertNotNull(movie);
        testForNullFieldsAndNewItems(movie);

        List<Integer> genreIds = movie.getGenreIds();
        assertNotNull(genreIds);
        assertFalse(genreIds.isEmpty());
        assertNotNull(genreIds.get(0));
    }

    /**
     * Test {@link TmdbMovieLists#getPopular(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetPopular() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/popular.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/popular?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovieLists tmdbMovieLists = getTmdbApi().getMovieLists();
        MovieResultsPage movieResultsPage = tmdbMovieLists.getPopular("en-US", 1, null);
        assertNotNull(movieResultsPage);
        testForNullFieldsAndNewItems(movieResultsPage);

        List<Movie> movies = movieResultsPage.getResults();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        Movie movie = movies.get(0);
        assertNotNull(movie);
        testForNullFieldsAndNewItems(movie);

        List<Integer> genreIds = movie.getGenreIds();
        assertNotNull(genreIds);
        assertFalse(genreIds.isEmpty());
        assertNotNull(genreIds.get(0));
    }

    /**
     * Test {@link TmdbMovieLists#getTopRated(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetTopRated() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/top_rated.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/top_rated?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovieLists tmdbMovieLists = getTmdbApi().getMovieLists();
        MovieResultsPage movieResultsPage = tmdbMovieLists.getTopRated("en-US", 1, null);
        assertNotNull(movieResultsPage);
        testForNullFieldsAndNewItems(movieResultsPage);

        List<Movie> movies = movieResultsPage.getResults();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        Movie movie = movies.get(0);
        assertNotNull(movie);
        testForNullFieldsAndNewItems(movie);

        List<Integer> genreIds = movie.getGenreIds();
        assertNotNull(genreIds);
        assertFalse(genreIds.isEmpty());
        assertNotNull(genreIds.get(0));
    }

    /**
     * Test {@link TmdbMovieLists#getUpcoming(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetUpcoming() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/upcoming.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/upcoming?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovieLists tmdbMovieLists = getTmdbApi().getMovieLists();
        MovieResultsPageWithDates movieResultsPageWithDates = tmdbMovieLists.getUpcoming("en-US", 1, null);
        assertNotNull(movieResultsPageWithDates);
        testForNullFieldsAndNewItems(movieResultsPageWithDates);

        Dates dates = movieResultsPageWithDates.getDates();
        assertNotNull(dates);
        testForNullFieldsAndNewItems(dates);

        List<Movie> movies = movieResultsPageWithDates.getResults();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        Movie movie = movies.get(0);
        assertNotNull(movie);
        testForNullFieldsAndNewItems(movie);

        List<Integer> genreIds = movie.getGenreIds();
        assertNotNull(genreIds);
        assertFalse(genreIds.isEmpty());
        assertNotNull(genreIds.get(0));
    }
}
