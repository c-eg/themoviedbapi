package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.certifications.CertificationResults;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbCertifications.TMDB_METHOD_CERTIFICATIONS;
import static info.movito.themoviedbapi.TmdbCertifications.TMDB_METHOD_MOVIE_CERTIFICATIONS;
import static info.movito.themoviedbapi.TmdbCertifications.TMDB_METHOD_TV_CERTIFICATIONS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CERTIFICATIONS + "/" + TMDB_METHOD_MOVIE_CERTIFICATIONS);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCertifications tmdbCertifications = getTmdbApi().getCertifications();
        CertificationResults movieCertifications = tmdbCertifications.getMovieCertifications();
        assertNotNull(movieCertifications);
        validateAbstractJsonMappingFields(movieCertifications);
    }

    /**
     * Test {@link TmdbCertifications#getTvCertifications()} with an expected result.
     */
    @Test
    public void testGetTvCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/certifications/tv.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CERTIFICATIONS + "/" + TMDB_METHOD_TV_CERTIFICATIONS);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCertifications tmdbCertifications = getTmdbApi().getCertifications();
        CertificationResults tvCertifications = tmdbCertifications.getTvCertifications();
        assertNotNull(tvCertifications);
        validateAbstractJsonMappingFields(tvCertifications);
    }
}
