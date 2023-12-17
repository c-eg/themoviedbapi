package uk.co.conoregan.themoviedbapi.api;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.TestUtils;
import uk.co.conoregan.themoviedbapi.model.changes.Change;
import uk.co.conoregan.themoviedbapi.model.changes.ChangesResultsPage;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbChanges}.
 */
public class TmdbChangesTest extends AbstractTmdbApiTest {
    /**
     * Tests the {@link TmdbChanges#getMovieChangesList(String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetMovieChangesList() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/changes/movie_list.json");
        mockResponse(body, 200);

        TmdbChanges tmdbChanges = getTmdbApi().getChanges();
        ChangesResultsPage changesResultsPage = tmdbChanges.getMovieChangesList("2023-01-13", "2023-01-14", 1);
        assertNotNull(changesResultsPage);
        testForNullFieldsAndUnknownProperties(changesResultsPage);

        Change change = changesResultsPage.getResults().get(0);
        assertNotNull(change);
        testForNullFieldsAndUnknownProperties(change);
        assertFalse(change.getAdult());
    }

    /**
     * Tests the {@link TmdbChanges#getPeopleChangesList(String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetPeopleChangesList() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/changes/people_list.json");
        mockResponse(body, 200);

        TmdbChanges tmdbChanges = getTmdbApi().getChanges();
        ChangesResultsPage changesResultsPage = tmdbChanges.getPeopleChangesList("2023-01-13", "2023-01-14", 1);
        assertNotNull(changesResultsPage);
        testForNullFieldsAndUnknownProperties(changesResultsPage);

        Change change = changesResultsPage.getResults().get(0);
        assertNotNull(change);
        testForNullFieldsAndUnknownProperties(change);
        assertFalse(change.getAdult());
    }

    /**
     * Tests the {@link TmdbChanges#getTvChangesList(String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetTvChangesList() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/changes/tv_list.json");
        mockResponse(body, 200);

        TmdbChanges tmdbChanges = getTmdbApi().getChanges();
        ChangesResultsPage changesResultsPage = tmdbChanges.getTvChangesList("2023-01-13", "2023-01-14", 1);
        assertNotNull(changesResultsPage);
        testForNullFieldsAndUnknownProperties(changesResultsPage);

        Change change = changesResultsPage.getResults().get(0);
        assertNotNull(change);
        testForNullFieldsAndUnknownProperties(change);
        assertFalse(change.getAdult());
    }
}
