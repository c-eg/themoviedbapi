package info.movito.themoviedbapi;

import java.io.IOException;
import java.net.URL;

import info.movito.themoviedbapi.model.changes.ChangesResultsPage;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbChanges.TMDB_METHOD_CHANGES;
import static info.movito.themoviedbapi.TmdbChanges.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.TmdbChanges.TMDB_METHOD_PERSON;
import static info.movito.themoviedbapi.TmdbChanges.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/" + TMDB_METHOD_CHANGES +
            "?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ChangesResultsPage changesResultsPage = getApiToTest().getMovieChangesList(startDate, endDate, page);
        assertNotNull(changesResultsPage);
        validateAbstractJsonMappingFields(changesResultsPage);
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
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/" + TMDB_METHOD_CHANGES +
            "?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ChangesResultsPage changesResultsPage = getApiToTest().getPeopleChangesList(startDate, endDate, page);
        assertNotNull(changesResultsPage);
        validateAbstractJsonMappingFields(changesResultsPage);
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
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/" + TMDB_METHOD_CHANGES +
            "?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ChangesResultsPage changesResultsPage = getApiToTest().getTvChangesList(startDate, endDate, page);
        assertNotNull(changesResultsPage);
        validateAbstractJsonMappingFields(changesResultsPage);
    }
}
