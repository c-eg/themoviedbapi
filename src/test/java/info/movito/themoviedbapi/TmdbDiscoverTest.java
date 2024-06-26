package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverTvParamBuilder;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbDiscover.TMDB_METHOD_DISCOVER;
import static info.movito.themoviedbapi.TmdbDiscover.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.TmdbDiscover.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbDiscover}.
 */
public class TmdbDiscoverTest extends AbstractTmdbApiTest<TmdbDiscover> {
    @Override
    public TmdbDiscover createApiToTest() {
        return getTmdbApi().getDiscover();
    }

    /**
     * Test for {@link TmdbDiscover#getMovie(DiscoverMovieParamBuilder)} with an expected result & search params from the builder.
     */
    @Test
    public void testGetMovie() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/movies.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_MOVIE + "?page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        DiscoverMovieParamBuilder discoverMovieParamBuilder = new DiscoverMovieParamBuilder()
            .page(1);

        MovieResultsPage movieResultsPage = getApiToTest().getMovie(discoverMovieParamBuilder);
        assertNotNull(movieResultsPage);
        validateAbstractJsonMappingFields(movieResultsPage);
    }

    /**
     * Test for {@link TmdbDiscover#getMovie(DiscoverMovieParamBuilder)} with an expected result, mull builder.
     */
    @Test
    public void testGetMovieNullBuilder() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/movies.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_MOVIE;
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = getApiToTest().getMovie(null);
        assertNotNull(movieResultsPage);
        validateAbstractJsonMappingFields(movieResultsPage);
    }

    /**
     * Test for {@link TmdbDiscover#getTv(DiscoverTvParamBuilder)} with an expected result & search params from the builder.
     */
    @Test
    public void testGetTv() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/tv.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_TV + "?page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        DiscoverTvParamBuilder discoverTvParamBuilder = new DiscoverTvParamBuilder()
            .page(1);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getTv(discoverTvParamBuilder);
        assertNotNull(tvSeriesResultsPage);
        validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }

    /**
     * Test for {@link TmdbDiscover#getTv(DiscoverTvParamBuilder)} with an expected result, null builder.
     */
    @Test
    public void testGetTvNullBuilder() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/tv.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_TV;
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().getTv(null);
        assertNotNull(tvSeriesResultsPage);
        validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }
}
