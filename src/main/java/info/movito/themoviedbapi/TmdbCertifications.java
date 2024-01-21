package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.certifications.CertificationResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for certifications. See the
 * <a href="https://developer.themoviedb.org/reference/certification-movie-list">documentation</a> for more info.
 */
public class TmdbCertifications extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_CERTIFICATIONS = "certification";

    protected static final String TMDB_METHOD_MOVIE_CERTIFICATIONS = "movie/list";

    protected static final String TMDB_METHOD_TV_CERTIFICATIONS = "tv/list";

    TmdbCertifications(TmdbApi tmdbApi) {
        super(tmdbApi);
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
        return mapJsonResult(apiUrl, CertificationResults.class);
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
        return mapJsonResult(apiUrl, CertificationResults.class);
    }
}
