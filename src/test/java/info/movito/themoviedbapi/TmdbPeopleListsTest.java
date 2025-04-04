package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbPeopleLists.TMDB_METHOD_PEOPLE_LISTS;
import static info.movito.themoviedbapi.testutil.TestUtils.validateAbstractJsonMappingFields;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        PopularPersonResultsPage popularPersonResultsPage = getApiToTest().getPopular("en-US", 1);
        assertNotNull(popularPersonResultsPage);
        validateAbstractJsonMappingFields(popularPersonResultsPage);
    }
}
