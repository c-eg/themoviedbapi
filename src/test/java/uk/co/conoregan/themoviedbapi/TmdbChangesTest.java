package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.changes.ChangesResultsPage;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbChanges.TMDB_METHOD_CHANGES;
import static uk.co.conoregan.themoviedbapi.TmdbChanges.TMDB_METHOD_MOVIE;
import static uk.co.conoregan.themoviedbapi.TmdbChanges.TMDB_METHOD_PERSON;
import static uk.co.conoregan.themoviedbapi.TmdbChanges.TMDB_METHOD_TV;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbChanges}.
 */
public class TmdbChangesTest extends AbstractTmdbApiTest<TmdbChanges> {
    @Override
    public TmdbChanges createApiToTest() {
        return getTmdbApi().getChanges();
    }

    /**
     * Tests the {@link TmdbChanges#getMovieChangesList(String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetMovieChangesList() throws TmdbException, IOException {
        String startDate = "2023-01-13";
        String endDate = "2023-01-14";
        int page = 1;

        String body = TestUtils.readTestFile("api_responses/changes/movie_list.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/" + TMDB_METHOD_CHANGES +
            "?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ChangesResultsPage changesResultsPage = getApiToTest().getMovieChangesList(startDate, endDate, page);
        assertNotNull(changesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(changesResultsPage);
    }

    /**
     * Tests the {@link TmdbChanges#getPeopleChangesList(String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetPeopleChangesList() throws TmdbException, IOException {
        String startDate = "2023-01-13";
        String endDate = "2023-01-14";
        int page = 1;

        String body = TestUtils.readTestFile("api_responses/changes/people_list.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/" + TMDB_METHOD_CHANGES +
            "?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ChangesResultsPage changesResultsPage = getApiToTest().getPeopleChangesList(startDate, endDate, page);
        assertNotNull(changesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(changesResultsPage);
    }

    /**
     * Tests the {@link TmdbChanges#getTvChangesList(String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetTvChangesList() throws TmdbException, IOException {
        String startDate = "2023-01-13";
        String endDate = "2023-01-14";
        int page = 1;

        String body = TestUtils.readTestFile("api_responses/changes/tv_list.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/" + TMDB_METHOD_CHANGES +
            "?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ChangesResultsPage changesResultsPage = getApiToTest().getTvChangesList(startDate, endDate, page);
        assertNotNull(changesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(changesResultsPage);
    }
}
