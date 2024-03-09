package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbKeywords.TMDB_METHOD_KEYWORD;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbKeywords}.
 */
public class TmdbKeywordsTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbKeywords#getDetails(int)} with an expected result.
     */
    @Test
    public void testGetDetails() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/keywords/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_KEYWORD + "/1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbKeywords tmdbKeywords = getTmdbApi().getKeywords();
        Keyword keyword = tmdbKeywords.getDetails(1);
        assertNotNull(keyword);
        validateAbstractJsonMappingFields(keyword);
    }
}
