package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.rated.RatedMovie;
import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisode;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeries;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.sortby.AccountSortBy;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbGuestSessions.TMDB_METHOD_GUEST_SESSIONS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbGuestSessions}.
 */
public class TmdbGuestSessionsTest extends AbstractTmdbApiTest {
    /**
     * Tests the {@link TmdbGuestSessions#getRatedMovies(int, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedMovies() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/guest_sessions/rated_movies.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_GUEST_SESSIONS + "/1/rated/movies?language=en&page=1&sort_by=created_at.desc");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbGuestSessions guestSessions = new TmdbGuestSessions(getTmdbApi());
        RatedMovieResultsPage ratedMovieResultsPage = guestSessions.getRatedMovies(1, "en", 1, AccountSortBy.CREATED_AT_DESC);
        assertNotNull(ratedMovieResultsPage);
        testForNullFieldsAndNewItems(ratedMovieResultsPage);

        List<RatedMovie> ratedMovies = ratedMovieResultsPage.getResults();
        assertFalse(ratedMovies.isEmpty());

        RatedMovie ratedMovie = ratedMovies.get(0);
        assertNotNull(ratedMovie);
        testForNullFieldsAndNewItems(ratedMovie);
    }

    /**
     * Tests the {@link TmdbGuestSessions#getRatedTvSeries(int, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvSeries() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/guest_sessions/rated_tv.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_GUEST_SESSIONS + "/1/rated/tv?language=en&page=1&sort_by=created_at.desc");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbGuestSessions guestSessions = new TmdbGuestSessions(getTmdbApi());
        RatedTvSeriesResultsPage ratedTvSeriesResultsPage = guestSessions.getRatedTvSeries(1, "en", 1, AccountSortBy.CREATED_AT_DESC);
        assertNotNull(ratedTvSeriesResultsPage);
        testForNullFieldsAndNewItems(ratedTvSeriesResultsPage);

        List<RatedTvSeries> ratedTvSeriess = ratedTvSeriesResultsPage.getResults();
        assertFalse(ratedTvSeriess.isEmpty());

        RatedTvSeries ratedTvSeries = ratedTvSeriess.get(0);
        assertNotNull(ratedTvSeries);
        testForNullFieldsAndNewItems(ratedTvSeries);
    }

    /**
     * Tests the {@link TmdbGuestSessions#getRatedTvEpisodes(int, String, Integer, AccountSortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvEpisodes() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/guest_sessions/rated_tv_episodes.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_GUEST_SESSIONS +
            "/1/rated/tv/episodes?language=en&page=1&sort_by=created_at.desc");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbGuestSessions guestSessions = new TmdbGuestSessions(getTmdbApi());
        RatedTvEpisodeResultsPage ratedTvEpisodesResultsPage = guestSessions.getRatedTvEpisodes(1, "en", 1, AccountSortBy.CREATED_AT_DESC);
        assertNotNull(ratedTvEpisodesResultsPage);
        testForNullFieldsAndNewItems(ratedTvEpisodesResultsPage);

        List<RatedTvEpisode> ratedTvEpisodes = ratedTvEpisodesResultsPage.getResults();
        assertFalse(ratedTvEpisodes.isEmpty());

        RatedTvEpisode ratedTvEpisode = ratedTvEpisodes.get(0);
        assertNotNull(ratedTvEpisode);
        testForNullFieldsAndNewItems(ratedTvEpisode);
    }
}
