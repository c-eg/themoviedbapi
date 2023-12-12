package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.certifications.CertificationResults;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

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
     * Get the list of supported certifications for movies.
     */
    public CertificationResults getMovieCertifications() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CERTIFICATIONS, "movie/list");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CertificationResults.class);
    }

    /**
     * Get the list of supported certifications for tv series.
     */
    public CertificationResults getTvCertifications() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CERTIFICATIONS, "tv/list");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CertificationResults.class);
    }
}
