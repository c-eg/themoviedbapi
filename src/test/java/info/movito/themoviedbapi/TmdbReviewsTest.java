package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.reviews.Review;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbReviews.TMDB_METHOD_MOVIE_REVIEW;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests {@link TmdbReviews}.
 */
public class TmdbReviewsTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbReviews#getDetails(int)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        int reviewId = 1;

        String body = TestUtils.readTestFile("api_responses/reviews/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_REVIEW + "/" + reviewId);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbReviews tmdbReviews = getTmdbApi().getReviews();
        Review review = tmdbReviews.getDetails(reviewId);
        assertNotNull(review);
        validateAbstractJsonMappingFields(review);
    }
}
