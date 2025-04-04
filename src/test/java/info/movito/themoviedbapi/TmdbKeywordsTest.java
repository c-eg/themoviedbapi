package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbKeywords.TMDB_METHOD_KEYWORD;
import static info.movito.themoviedbapi.testutil.TestUtils.validateAbstractJsonMappingFields;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbKeywords}.
 */
public class TmdbKeywordsTest extends AbstractTmdbApiTest<TmdbKeywords> {
    @Override
    public TmdbKeywords createApiToTest() {
        return getTmdbApi().getKeywords();
    }

    /**
     * Tests {@link TmdbKeywords#getDetails(int)} with an expected result.
     */
    @Test
    public void testGetDetails() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/keywords/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_KEYWORD + "/1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Keyword keyword = getApiToTest().getDetails(1);
        assertNotNull(keyword);
        validateAbstractJsonMappingFields(keyword);
    }
}
