package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;

import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.sortby.AccountSortBy;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbGuestSessions.TMDB_METHOD_GUEST_SESSIONS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbGuestSessions}.
 */
public class TmdbGuestSessionsTest extends AbstractTmdbApiTest<TmdbGuestSessions> {
    @Override
    public TmdbGuestSessions createApiToTest() {
        return getTmdbApi().getGuestSessions();
    }

    /**
     * Tests the {@link TmdbGuestSessions#getRatedMovies(int, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedMovies() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/guest_sessions/rated_movies.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_GUEST_SESSIONS + "/1/rated/movies?language=en&page=1&sort_by=created_at.desc";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RatedMovieResultsPage ratedMovieResultsPage = getApiToTest().getRatedMovies(1, "en", 1, AccountSortBy.CREATED_AT_DESC);
        assertNotNull(ratedMovieResultsPage);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of("info.movito.themoviedbapi.model.rated.RatedMovieResultsPage.results.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(ratedMovieResultsPage, validatorConfig);
    }

    /**
     * Tests the {@link TmdbGuestSessions#getRatedTvSeries(int, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvSeries() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/guest_sessions/rated_tv.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_GUEST_SESSIONS + "/1/rated/tv?language=en&page=1&sort_by=created_at.desc";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RatedTvSeriesResultsPage ratedTvSeriesResultsPage = getApiToTest().getRatedTvSeries(1, "en", 1, AccountSortBy.CREATED_AT_DESC);
        assertNotNull(ratedTvSeriesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(ratedTvSeriesResultsPage);
    }

    /**
     * Tests the {@link TmdbGuestSessions#getRatedTvEpisodes(int, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvEpisodes() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/guest_sessions/rated_tv_episodes.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_GUEST_SESSIONS +
            "/1/rated/tv/episodes?language=en&page=1&sort_by=created_at.desc";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        RatedTvEpisodeResultsPage ratedTvEpisodesResultsPage = getApiToTest().getRatedTvEpisodes(1, "en", 1,
            AccountSortBy.CREATED_AT_DESC);
        assertNotNull(ratedTvEpisodesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(ratedTvEpisodesResultsPage);
    }
}
