package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbPeopleLists.TMDB_METHOD_PEOPLE_LISTS;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbPeopleLists}.
 */
public class TmdbPeopleListsTest extends AbstractTmdbApiTest<TmdbPeopleLists> {
    @Override
    public TmdbPeopleLists createApiToTest() {
        return getTmdbApi().getPeopleLists();
    }

    /**
     * Test {@link TmdbPeopleLists#getPopular(String, Integer)} with an expected result.
     */
    @Test
    public void testGetPopular() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people_lists/popular.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PEOPLE_LISTS + "?language=en-US&page=1";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        PopularPersonResultsPage popularPersonResultsPage = getApiToTest().getPopular("en-US", 1);
        assertNotNull(popularPersonResultsPage);
        TestUtils.validateAbstractJsonMappingFields(popularPersonResultsPage);
    }
}
