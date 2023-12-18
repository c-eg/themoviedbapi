package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Reviews;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for reviews. See the
 * <a href="https://developer.themoviedb.org/reference/review-details">documentation</a> for more info.
 */
public class TmdbReviews extends AbstractTmdbApi {
    private static final String TMDB_METHOD_REVIEW = "review";

    /**
     * Create a new TmdbReviews instance to call the reviews related TMDb API methods.
     */
    TmdbReviews(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get the reviews for a movie.
     *
     * @param movieId the movies id
     * @param language the language
     * @param page the page
     * @return the reviews
     */
    public ReviewResultsPage getReviews(int movieId, String language, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TmdbMovies.TMDB_METHOD_MOVIE, movieId, "reviews");
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ReviewResultsPage.class);
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
    public static class ReviewResultsPage extends ResultsPage<Reviews> {

    }
}
