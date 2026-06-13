package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.reviews.Review;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbApiClient;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for reviews. See the
 * <a href="https://developer.themoviedb.org/reference/review-details">documentation</a> for more info.
 */
public class TmdbReviews {
    protected static final String TMDB_METHOD_MOVIE_REVIEW = "reviews";

    private final TmdbApiClient tmdbApiClient;

    /**
     * Create a new TmdbReviews instance to call the reviews related TMDb API methods.
     */
    TmdbReviews(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Get the details for a review.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/review-details">documentation</a> for more info.</p>
     *
     * @param reviewId The review id.
     * @return the reviews
     */
    public Review getDetails(int reviewId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE_REVIEW, reviewId);
        return tmdbApiClient.get(apiUrl, Review.class);
    }
}
