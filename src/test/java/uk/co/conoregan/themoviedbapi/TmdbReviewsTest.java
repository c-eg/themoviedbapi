package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.reviews.Review;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbReviews.TMDB_METHOD_MOVIE_REVIEW;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Review review = getApiToTest().getDetails(reviewId);
        assertNotNull(review);
        TestUtils.validateAbstractJsonMappingFields(review);
    }
}
