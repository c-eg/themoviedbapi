package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Review;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for reviews. See the
 * <a href="https://developer.themoviedb.org/reference/review-details">documentation</a> for more info.
 */
public class TmdbReviews extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_MOVIE_REVIEW = "reviews";

    /**
     * Create a new TmdbReviews instance to call the reviews related TMDb API methods.
     */
    TmdbReviews(TmdbApi tmdbApi) {
        super(tmdbApi);
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
        return mapJsonResult(apiUrl, Review.class);
    }
}
