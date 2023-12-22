package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.certifications.Certification;
import info.movito.themoviedbapi.model.certifications.CertificationResults;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbCertifications}.
 */
public class TmdbCertificationsTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbCertifications#getMovieCertifications()} with an expected result.
     */
    @Test
    public void testGetMovieCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/certifications/movie.json");
        mockResponse(body, 200);

        TmdbCertifications tmdbCertifications = getTmdbApi().getCertifications();
        CertificationResults movieCertifications = tmdbCertifications.getMovieCertifications();
        assertNotNull(movieCertifications);
        testForNullFieldsAndUnknownProperties(movieCertifications);

        Map<String, List<Certification>> certifications = movieCertifications.getCertifications();
        assertFalse(certifications.isEmpty());

        List<Certification> auCertifications = certifications.get("AU");
        assertFalse(auCertifications.isEmpty());

        Certification auCertification = auCertifications.get(0);
        assertNotNull(auCertification);
        testForNullFieldsAndUnknownProperties(auCertification);
    }

    /**
     * Test {@link TmdbCertifications#getTvCertifications()} with an expected result.
     */
    @Test
    public void testGetTvCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/certifications/tv.json");
        mockResponse(body, 200);

        TmdbCertifications tmdbCertifications = getTmdbApi().getCertifications();
        CertificationResults tvCertifications = tmdbCertifications.getTvCertifications();
        assertNotNull(tvCertifications);
        testForNullFieldsAndUnknownProperties(tvCertifications);

        Map<String, List<Certification>> certifications = tvCertifications.getCertifications();
        assertFalse(certifications.isEmpty());

        List<Certification> auCertifications = certifications.get("AU");
        assertFalse(auCertifications.isEmpty());

        Certification auCertification = auCertifications.get(0);
        assertNotNull(auCertification);
        testForNullFieldsAndUnknownProperties(auCertification);
    }
}
