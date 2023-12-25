package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.credits.Media;
import info.movito.themoviedbapi.model.credits.MediaCredit;
import info.movito.themoviedbapi.model.credits.PersonCredit;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbCredits}.
 */
public class TmdbCreditsTest extends AbstractTmdbApiTest {
    /**
     * Test for {@link TmdbCredits#getCredits(String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/credits/details.json");
        mockResponse(body, 200);

        TmdbCredits tmdbCredits = getTmdbApi().getCredits();
        MediaCredit mediaCredit = tmdbCredits.getCredits("52fe45b59251416c9103bb99");
        assertNotNull(mediaCredit);
        testForNullFieldsAndUnknownProperties(mediaCredit);

        Media media = mediaCredit.getMedia();
        assertNotNull(media);
        testForNullFieldsAndUnknownProperties(media);

        PersonCredit personCredit = mediaCredit.getPersonCredit();
        assertNotNull(personCredit);
        testForNullFieldsAndUnknownProperties(personCredit);
    }
}
