package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.reviews.Review;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbReviews.TMDB_METHOD_MOVIE_REVIEW;
import static info.movito.themoviedbapi.testutil.TestUtils.validateAbstractJsonMappingFields;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests {@link TmdbReviews}.
 */
public class TmdbReviewsTest extends AbstractTmdbApiTest<TmdbReviews> {
    @Override
    public TmdbReviews createApiToTest() {
        return getTmdbApi().getReviews();
    }

    /**
     * Test {@link TmdbReviews#getDetails(int)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        int reviewId = 1;

        String body = TestUtils.readTestFile("api_responses/reviews/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_REVIEW + "/" + reviewId;
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Review review = getApiToTest().getDetails(reviewId);
        assertNotNull(review);
        validateAbstractJsonMappingFields(review);
    }
}
