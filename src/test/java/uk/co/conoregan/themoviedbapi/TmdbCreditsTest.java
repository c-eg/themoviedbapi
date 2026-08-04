package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.credits.Credit;
import uk.co.conoregan.themoviedbapi.model.credits.CreditType;
import uk.co.conoregan.themoviedbapi.model.credits.MediaType;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbCredits.TMDB_METHOD_CREDITS;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests {@link TmdbCredits}.
 */
public class TmdbCreditsTest extends AbstractTmdbApiTest<TmdbCredits> {
    @Override
    public TmdbCredits createApiToTest() {
        return getTmdbApi().getCredits();
    }

    /**
     * Tests the method {@link TmdbCredits#getDetails(String, String)} with an expected result, for a tv credit.
     */
    @Test
    public void testGetDetailsTv() throws IOException, TmdbException {
        String creditId = "6024a814c0ae36003d59cc3c";

        String body = TestUtils.readTestFile("api_responses/credits/details_tv.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CREDITS + "/" + creditId + "?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Credit credit = getApiToTest().getDetails(creditId, "en-US");
        assertNotNull(credit);

        assertEquals(CreditType.CAST, credit.getCreditType());
        assertEquals(MediaType.TV, credit.getMediaType());

        TestUtils.validateAbstractJsonMappingFields(credit);
    }

    /**
     * Tests the method {@link TmdbCredits#getDetails(String, String)} with an expected result, for a movie credit.
     */
    @Test
    public void testGetDetailsMovie() throws IOException, TmdbException {
        String creditId = "630cbd43ede1b00083c3badf";

        String body = TestUtils.readTestFile("api_responses/credits/details_movie.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CREDITS + "/" + creditId + "?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Credit credit = getApiToTest().getDetails(creditId, "en-US");
        assertNotNull(credit);

        assertEquals(MediaType.MOVIE, credit.getMediaType());
        TestUtils.validateAbstractJsonMappingFields(credit);
    }
}
