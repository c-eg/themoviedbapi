package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeries;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverTvParamBuilder;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbDiscover.TMDB_METHOD_DISCOVER;
import static info.movito.themoviedbapi.TmdbDiscover.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.TmdbDiscover.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbDiscover}.
 */
public class TmdbDiscoverTest extends AbstractTmdbApiTest {
    /**
     * Test for {@link TmdbDiscover#getMovie(DiscoverMovieParamBuilder)} with an expected result & search params from the builder.
     */
    @Test
    public void testGetMovie() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/movies.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_MOVIE + "?page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbDiscover tmdbDiscover = getTmdbApi().getDiscover();
        DiscoverMovieParamBuilder discoverMovieParamBuilder = new DiscoverMovieParamBuilder()
            .page(1);

        MovieResultsPage movieResultsPage = tmdbDiscover.getMovie(discoverMovieParamBuilder);
        assertNotNull(movieResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(movieResultsPage);

        Movie movie = movieResultsPage.getResults().get(0);
        assertNotNull(movie);
        checkForNullAndEmptyFieldsAndNewItems(movie);
    }

    /**
     * Test for {@link TmdbDiscover#getMovie(DiscoverMovieParamBuilder)} with an expected result, mull builder.
     */
    @Test
    public void testGetMovieNullBuilder() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/movies.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_MOVIE);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbDiscover tmdbDiscover = getTmdbApi().getDiscover();
        MovieResultsPage movieResultsPage = tmdbDiscover.getMovie(null);
        assertNotNull(movieResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(movieResultsPage);

        Movie movie = movieResultsPage.getResults().get(0);
        assertNotNull(movie);
        checkForNullAndEmptyFieldsAndNewItems(movie);
    }

    /**
     * Test for {@link TmdbDiscover#getTv(DiscoverTvParamBuilder)} with an expected result & search params from the builder.
     */
    @Test
    public void testGetTv() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/tv.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_TV + "?page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbDiscover tmdbDiscover = getTmdbApi().getDiscover();
        DiscoverTvParamBuilder discoverTvParamBuilder = new DiscoverTvParamBuilder()
            .page(1);

        TvSeriesResultsPage tvSeriesResultsPage = tmdbDiscover.getTv(discoverTvParamBuilder);
        assertNotNull(tvSeriesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(tvSeriesResultsPage);

        TvSeries tvSeries = tvSeriesResultsPage.getResults().get(0);
        assertNotNull(tvSeries);
        checkForNullAndEmptyFieldsAndNewItems(tvSeries);
    }

    /**
     * Test for {@link TmdbDiscover#getTv(DiscoverTvParamBuilder)} with an expected result, null builder.
     */
    @Test
    public void testGetTvNullBuilder() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/discover/tv.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_DISCOVER + "/" + TMDB_METHOD_TV);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbDiscover tmdbDiscover = getTmdbApi().getDiscover();
        TvSeriesResultsPage tvSeriesResultsPage = tmdbDiscover.getTv(null);
        assertNotNull(tvSeriesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(tvSeriesResultsPage);

        TvSeries tvSeries = tvSeriesResultsPage.getResults().get(0);
        assertNotNull(tvSeries);
        checkForNullAndEmptyFieldsAndNewItems(tvSeries);
    }
}
