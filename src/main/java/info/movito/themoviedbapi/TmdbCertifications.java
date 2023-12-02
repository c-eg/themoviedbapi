package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.certifications.CertificationResults;
import info.movito.themoviedbapi.tools.ApiUrl;

/**
 * The movie database api for certifications. See the
 * <a href="https://developer.themoviedb.org/reference/certification-movie-list">documentation</a> for more info.
 */
public class TmdbCertifications extends AbstractTmdbApi {
    private static final String TMDB_METHOD_CERTIFICATIONS = "certification";

    TmdbCertifications(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get the list of supported certifications for movies.
     */
    public CertificationResults getMovieCertifications() {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_CERTIFICATIONS, "movie/list");
        return mapJsonResult(apiUrl, CertificationResults.class);
    }

    /**
     * Get the list of supported certifications for tv series.
     */
    public CertificationResults getTvCertifications() {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_CERTIFICATIONS, "tv/list");
        return mapJsonResult(apiUrl, CertificationResults.class);
    }
}
