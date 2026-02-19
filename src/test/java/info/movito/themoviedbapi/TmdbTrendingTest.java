package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.multi.MultiResultsPage;
import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.model.time.TimeWindow;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbTrending.TMDB_METHOD_TRENDING;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
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
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/all/week?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MultiResultsPage allResults = getApiToTest().getAll(TimeWindow.WEEK, "en-US");
        assertNotNull(allResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.core.multi.MultiResultsPage.results.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(allResults, validatorConfig);
    }

    /**
     * Tests {@link TmdbTrending#getMovies(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetMovies() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/movies.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/movie/week?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResults = getApiToTest().getMovies(TimeWindow.WEEK, "en-US");
        assertNotNull(movieResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of("info.movito.themoviedbapi.model.core.MovieResultsPage.results.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movieResults, validatorConfig);
    }

    /**
     * Tests {@link TmdbTrending#getPeople(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetPeople() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/people.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/person/week?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        PopularPersonResultsPage peopleResults = getApiToTest().getPeople(TimeWindow.WEEK, "en-US");
        assertNotNull(peopleResults);
        TestUtils.validateAbstractJsonMappingFields(peopleResults);
    }

    /**
     * Tests {@link TmdbTrending#getTv(TimeWindow, String)} with an expected result.
     */
    @Test
    public void testGetTv() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/trending/tv.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TRENDING + "/tv/week?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvResults = getApiToTest().getTv(TimeWindow.WEEK, "en-US");
        assertNotNull(tvResults);
        TestUtils.validateAbstractJsonMappingFields(tvResults);
    }
}
