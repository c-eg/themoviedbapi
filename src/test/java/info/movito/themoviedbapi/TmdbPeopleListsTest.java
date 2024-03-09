package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.peoplelists.PopularPersonResults;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbPeopleLists.TMDB_METHOD_PEOPLE_LISTS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbPeopleLists}.
 */
public class TmdbPeopleListsTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbPeopleLists#getPopular(String, Integer)} with an expected result.
     */
    @Test
    public void testGetPopular() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people_lists/popular.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PEOPLE_LISTS + "?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeopleLists tmdbPeopleLists = getTmdbApi().getPeopleLists();
        PopularPersonResults popularPersonResults = tmdbPeopleLists.getPopular("en-US", 1);
        assertNotNull(popularPersonResults);
        validateAbstractJsonMappingFields(popularPersonResults);
    }
}
