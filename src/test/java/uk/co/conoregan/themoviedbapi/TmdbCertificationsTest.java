package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.certifications.CertificationResults;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbCertifications.TMDB_METHOD_CERTIFICATIONS;
import static uk.co.conoregan.themoviedbapi.TmdbCertifications.TMDB_METHOD_MOVIE_CERTIFICATIONS;
import static uk.co.conoregan.themoviedbapi.TmdbCertifications.TMDB_METHOD_TV_CERTIFICATIONS;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbCertifications}.
 */
public class TmdbCertificationsTest extends AbstractTmdbApiTest<TmdbCertifications> {
    @Override
    public TmdbCertifications createApiToTest() {
        return getTmdbApi().getCertifications();
    }

    /**
     * Test {@link TmdbCertifications#getMovieCertifications()} with an expected result.
     */
    @Test
    public void testGetMovieCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/certifications/movie.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CERTIFICATIONS + "/" + TMDB_METHOD_MOVIE_CERTIFICATIONS;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        CertificationResults movieCertifications = getApiToTest().getMovieCertifications();
        assertNotNull(movieCertifications);
        TestUtils.validateAbstractJsonMappingFields(movieCertifications);
    }

    /**
     * Test {@link TmdbCertifications#getTvCertifications()} with an expected result.
     */
    @Test
    public void testGetTvCertifications() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/certifications/tv.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CERTIFICATIONS + "/" + TMDB_METHOD_TV_CERTIFICATIONS;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        CertificationResults tvCertifications = getApiToTest().getTvCertifications();
        assertNotNull(tvCertifications);
        TestUtils.validateAbstractJsonMappingFields(tvCertifications);
    }
}
