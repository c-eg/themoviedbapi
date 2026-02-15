package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbTvSeriesLists}.
 */
public class TmdbTvSeriesListsTest extends AbstractTmdbApiTest<TmdbTvSeriesLists> {
    @Override
    public TmdbTvSeriesLists createApiToTest() {
        return getTmdbApi().getTvSeriesLists();
    }

    /**
     * Tests {@link TmdbTvSeriesLists#getAiringToday(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetAiringToday() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series_lists/airing_today.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/airing_today?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getAiringToday("en-US", null, null);
        assertNotNull(tvSeriesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }

    /**
     * Tests {@link TmdbTvSeriesLists#getOnTheAir(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetOnTheAir() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series_lists/on_the_air.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/on_the_air?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getOnTheAir("en-US", null, null);
        assertNotNull(tvSeriesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }

    /**
     * Tests {@link TmdbTvSeriesLists#getPopular(String, Integer)} with an expected result.
     */
    @Test
    public void testGetPopular() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series_lists/popular.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/popular?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getPopular("en-US", null);
        assertNotNull(tvSeriesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }

    /**
     * Tests {@link TmdbTvSeriesLists#getTopRated(String, Integer)} with an expected result.
     */
    @Test
    public void testGetTopRated() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series_lists/top_rated.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/top_rated?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getTopRated("en-US", null);
        assertNotNull(tvSeriesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }
}
