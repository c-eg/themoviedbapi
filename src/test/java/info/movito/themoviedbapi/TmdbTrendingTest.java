package info.movito.themoviedbapi;

import java.io.IOException;
import java.net.URL;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.multi.MultiResultsPage;
import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.model.time.TimeWindow;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbTrending.TMDB_METHOD_TRENDING;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbTrending}.
 */
public class TmdbTrendingTest extends AbstractTmdbApiTest<TmdbTrending> {
    @Override
    public TmdbTrending createApiToTest() {
        return getTmdbApi().getTrending();
    }

    /**
     * Tests {@link TmdbTrending#getAll(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetAll() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/all.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/all/week?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MultiResultsPage allResults = getApiToTest().getAll(TimeWindow.WEEK, "en-US");
        assertNotNull(allResults);
        validateAbstractJsonMappingFields(allResults);
    }

    /**
     * Tests {@link TmdbTrending#getMovies(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetMovies() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/movies.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/movie/week?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResults = getApiToTest().getMovies(TimeWindow.WEEK, "en-US");
        assertNotNull(movieResults);
        validateAbstractJsonMappingFields(movieResults);
    }

    /**
     * Tests {@link TmdbTrending#getPeople(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetPeople() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/people.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/person/week?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        PopularPersonResultsPage peopleResults = getApiToTest().getPeople(TimeWindow.WEEK, "en-US");
        assertNotNull(peopleResults);
        validateAbstractJsonMappingFields(peopleResults);
    }

    /**
     * Tests {@link TmdbTrending#getTv(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetTv() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/tv.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/tv/week?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvResults = getApiToTest().getTv(TimeWindow.WEEK, "en-US");
        assertNotNull(tvResults);
        validateAbstractJsonMappingFields(tvResults);
    }
}
