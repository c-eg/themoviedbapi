package uk.co.conoregan.themoviedbapi.api;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.TestUtils;
import uk.co.conoregan.themoviedbapi.model.certifications.Certification;
import uk.co.conoregan.themoviedbapi.model.certifications.CertificationResults;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link uk.co.conoregan.themoviedbapi.api.TmdbCertifications}.
 */
public class TmdbCertificationsTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbCertifications#getMovieCertifications()} with an expected result.
     */
    @Test
    public void testGetMovieCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("certifications/movie.json");
        mockResponse(body, 200);

        TmdbCertifications tmdbCertifications = new TmdbCertifications(getTmdbApi());
        CertificationResults movieCertifications = tmdbCertifications.getMovieCertifications();
        assertNotNull(movieCertifications);
        assertTrue(movieCertifications.getUnknownProperties().isEmpty()); // check for no unknown properties

        Map<String, List<Certification>> certifications = movieCertifications.getCertifications();
        assertFalse(certifications.isEmpty());

        List<Certification> auCertifications = certifications.get("AU");
        assertFalse(auCertifications.isEmpty());

        Certification auCertification = auCertifications.get(0);
        assertNotNull(auCertification);
        assertEquals("E", auCertification.getCertification());
        assertEquals("Exempt from classification. Films that are exempt from classification must not" +
            " contain contentious material (i.e. material that would ordinarily be rated M or higher).",
            auCertification.getMeaning());
        assertEquals(1, auCertification.getOrder());
    }

    /**
     * Test {@link TmdbCertifications#getTvCertifications()} with an expected result.
     */
    @Test
    public void testGetTvCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("certifications/tv.json");
        mockResponse(body, 200);

        TmdbCertifications tmdbCertifications = new TmdbCertifications(getTmdbApi());
        CertificationResults tvCertifications = tmdbCertifications.getTvCertifications();
        assertNotNull(tvCertifications);
        assertTrue(tvCertifications.getUnknownProperties().isEmpty()); // check for no unknown properties

        Map<String, List<Certification>> certifications = tvCertifications.getCertifications();
        assertFalse(certifications.isEmpty());

        List<Certification> auCertifications = certifications.get("AU");
        assertFalse(auCertifications.isEmpty());

        Certification auCertification = auCertifications.get(0);
        assertNotNull(auCertification);
        assertEquals("P", auCertification.getCertification());
        assertEquals("Programming is intended for younger children 2–11; commercial stations must show " +
                "at least 30 minutes of P-rated content each weekday and weekends at all times. " +
                "No advertisements may be shown during P-rated programs.",
            auCertification.getMeaning());
        assertEquals(1, auCertification.getOrder());
    }
}
