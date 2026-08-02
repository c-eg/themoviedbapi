package uk.co.conoregan.themoviedbapi;

import uk.co.conoregan.themoviedbapi.model.certifications.CertificationResults;
import uk.co.conoregan.themoviedbapi.tools.ApiUrl;
import uk.co.conoregan.themoviedbapi.tools.TmdbApiClient;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for certifications. See the
 * <a href="https://developer.themoviedb.org/reference/certification-movie-list">documentation</a> for more info.
 */
public class TmdbCertifications {
    protected static final String TMDB_METHOD_CERTIFICATIONS = "certification";

    protected static final String TMDB_METHOD_MOVIE_CERTIFICATIONS = "movie/list";

    protected static final String TMDB_METHOD_TV_CERTIFICATIONS = "tv/list";

    private final TmdbApiClient tmdbApiClient;

    TmdbCertifications(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Get an up to date list of the officially supported movie certifications on TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/certification-movie-list">documentation</a> for more info.</p>
     *
     * @return The movie certifications.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public CertificationResults getMovieCertifications() throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_CERTIFICATIONS, TMDB_METHOD_MOVIE_CERTIFICATIONS);
        return tmdbApiClient.get(apiUrl, CertificationResults.class);
    }

    /**
     * <p>Get an up to date list of the officially supported tv certifications on TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/certifications-tv-list">documentation</a> for more info.</p>
     *
     * @return The tv certifications.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public CertificationResults getTvCertifications() throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_CERTIFICATIONS, TMDB_METHOD_TV_CERTIFICATIONS);
        return tmdbApiClient.get(apiUrl, CertificationResults.class);
    }
}
