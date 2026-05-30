package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.certifications.CertificationResults;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbRequest;
import info.movito.themoviedbapi.tools.TmdbResponse;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbCertifications.TMDB_METHOD_CERTIFICATIONS;
import static info.movito.themoviedbapi.TmdbCertifications.TMDB_METHOD_MOVIE_CERTIFICATIONS;
import static info.movito.themoviedbapi.TmdbCertifications.TMDB_METHOD_TV_CERTIFICATIONS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
