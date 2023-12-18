package info.movito.themoviedbapi;

import org.junit.jupiter.api.Test;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.MovieListResultsPage;
import info.movito.themoviedbapi.model.account.Movie;
import info.movito.themoviedbapi.model.account.MovieResultsPage;
import info.movito.themoviedbapi.model.account.TvSeries;
import info.movito.themoviedbapi.model.account.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.rated.RatedMovie;
import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisode;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeries;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.tools.SortBy;
import info.movito.themoviedbapi.tools.TmdbException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbAccount}.
 */
public class TmdbAccountTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbAccount#getDetails(String)} with an expected result.
     */
    @Test
    public void testGetAccount() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/account/details.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        Account account = tmdbAccount.getDetails("testSessionId");
        assertNotNull(account);
        testForNullFieldsAndUnknownProperties(account);

        assertEquals("c9e9fc152ee756a900db85757c29815d", account.getAvatar().getGravatarHash());
        assertEquals("/xy44UvpbTgzs9kWmp4C3fEaCl5h.png", account.getAvatar().getTmdbAvatarPath());
        assertEquals(548, account.getId());
        assertEquals("en", account.getIso6391());
        assertEquals("CA", account.getIso31661());
        assertEquals("Travis Bell", account.getName());
        assertFalse(account.getIncludeAdult());
        assertEquals("travisbell", account.getUsername());
    }

    /**
     * Test {@link TmdbAccount#addFavorite(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddFavourite() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("api_responses/account/add_favourite.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.addFavorite(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        testForNullFieldsAndUnknownProperties(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#removeFavorite(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFavorite() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("api_responses/account/add_favourite.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.removeFavorite(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        testForNullFieldsAndUnknownProperties(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#addToWatchList(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddToWatchList() throws IOException, TmdbException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("api_responses/account/add_to_watchlist.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.addToWatchList(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        testForNullFieldsAndUnknownProperties(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#removeFromWatchList(Integer, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFromWatchList() throws IOException, TmdbException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("api_responses/account/add_to_watchlist.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        ResponseStatus responseStatus = tmdbAccount.removeFromWatchList(accountId, sessionId, mediaId, mediaType);
        assertNotNull(responseStatus);
        testForNullFieldsAndUnknownProperties(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#getFavoriteMovies(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetFavouriteMovies() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/favourite_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        MovieResultsPage movieResultsPage = tmdbAccount.getFavoriteMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(movieResultsPage);
        testForNullFieldsAndUnknownProperties(movieResultsPage);
        assertEquals(1, movieResultsPage.getPage());
        assertEquals(4, movieResultsPage.getTotalPages());
        assertEquals(80, movieResultsPage.getTotalResults());

        List<Movie> movieResults = movieResultsPage.getResults();
        assertEquals(20, movieResults.size());

        Movie movie = movieResults.get(0);
        testForNullFieldsAndUnknownProperties(movie);
        assertEquals(9806, movie.getId());
    }

    /**
     * Test {@link TmdbAccount#getFavoriteTv(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetFavouriteTv() throws IOException, TmdbException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/favourite_tv.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        TvSeriesResultsPage tvSeriesResultsPage = tmdbAccount.getFavoriteTv(accountId, sessionId, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        testForNullFieldsAndUnknownProperties(tvSeriesResultsPage);
        assertEquals(1, tvSeriesResultsPage.getPage());
        assertEquals(4, tvSeriesResultsPage.getTotalPages());
        assertEquals(68, tvSeriesResultsPage.getTotalResults());

        List<TvSeries> tvResults = tvSeriesResultsPage.getResults();
        assertEquals(20, tvResults.size());

        TvSeries tvSeries = tvResults.get(0);
        testForNullFieldsAndUnknownProperties(tvSeries);
        assertEquals(1396, tvSeries.getId());
    }

    /**
     * Test {@link TmdbAccount#getLists(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        Integer page = 1;

        String body = TestUtils.readTestFile("api_responses/account/lists.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        MovieListResultsPage movieListResultsPage = tmdbAccount.getLists(accountId, sessionId, page);
        assertNotNull(movieListResultsPage);
        testForNullFieldsAndUnknownProperties(movieListResultsPage);
        assertEquals(1, movieListResultsPage.getPage());
        assertEquals(2, movieListResultsPage.getTotalPages());
        assertEquals(25, movieListResultsPage.getTotalResults());

        List<MovieList> movieLists = movieListResultsPage.getResults();
        assertEquals(20, movieLists.size());

        MovieList movieList = movieLists.get(0);
        testForNullFieldsAndUnknownProperties(movieList);
        assertEquals(120174, movieList.getId());
    }

    /**
     * Test {@link TmdbAccount#getRatedMovies(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetRatedMovies() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/rated_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        RatedMovieResultsPage ratedMovieResultsPage = tmdbAccount.getRatedMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedMovieResultsPage);
        testForNullFieldsAndUnknownProperties(ratedMovieResultsPage);
        assertEquals(1, ratedMovieResultsPage.getPage());
        assertEquals(47, ratedMovieResultsPage.getTotalPages());
        assertEquals(940, ratedMovieResultsPage.getTotalResults());

        List<RatedMovie> ratedMovies = ratedMovieResultsPage.getResults();
        assertEquals(16, ratedMovies.size());

        RatedMovie ratedMovie = ratedMovies.get(0);
        testForNullFieldsAndUnknownProperties(ratedMovie);
        assertEquals(120, ratedMovie.getId());
    }

    /**
     * Test {@link TmdbAccount#getRatedTvSeries(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvSeries() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/rated_tv.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        RatedTvSeriesResultsPage ratedTvSeriesResultsPage = tmdbAccount.getRatedTvSeries(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedTvSeriesResultsPage);
        testForNullFieldsAndUnknownProperties(ratedTvSeriesResultsPage);
        assertEquals(1, ratedTvSeriesResultsPage.getPage());
        assertEquals(15, ratedTvSeriesResultsPage.getTotalPages());
        assertEquals(290, ratedTvSeriesResultsPage.getTotalResults());

        List<RatedTvSeries> ratedTvSeriess = ratedTvSeriesResultsPage.getResults();
        assertEquals(20, ratedTvSeriess.size());

        RatedTvSeries ratedTvSeries = ratedTvSeriess.get(0);
        testForNullFieldsAndUnknownProperties(ratedTvSeries);
        assertEquals(1705, ratedTvSeries.getId());
    }

    /**
     * Test {@link TmdbAccount#getRatedTvEpisodes(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvEpisodes() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/rated_tv_episodes.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        RatedTvEpisodeResultsPage ratedTvEpisodesResultsPage =
            tmdbAccount.getRatedTvEpisodes(accountId, sessionId, language, page, sortBy);
        assertNotNull(ratedTvEpisodesResultsPage);
        testForNullFieldsAndUnknownProperties(ratedTvEpisodesResultsPage);
        assertEquals(1, ratedTvEpisodesResultsPage.getPage());
        assertEquals(10, ratedTvEpisodesResultsPage.getTotalPages());
        assertEquals(186, ratedTvEpisodesResultsPage.getTotalResults());

        List<RatedTvEpisode> ratedTvEpisodes = ratedTvEpisodesResultsPage.getResults();
        assertEquals(20, ratedTvEpisodes.size());

        RatedTvEpisode ratedTvEpisode = ratedTvEpisodes.get(0);
        testForNullFieldsAndUnknownProperties(ratedTvEpisode);
        assertEquals(64782, ratedTvEpisode.getId());
    }

    /**
     * Test {@link TmdbAccount#getWatchListMovies(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetWatchListMovies() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/watchlist_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        MovieResultsPage movieResultsPage = tmdbAccount.getWatchListMovies(accountId, sessionId, language, page, sortBy);
        assertNotNull(movieResultsPage);
        testForNullFieldsAndUnknownProperties(movieResultsPage);
        assertEquals(1, movieResultsPage.getPage());
        assertEquals(34, movieResultsPage.getTotalPages());
        assertEquals(677, movieResultsPage.getTotalResults());

        List<Movie> movies = movieResultsPage.getResults();
        assertEquals(20, movies.size());

        Movie movie = movies.get(0);
        testForNullFieldsAndUnknownProperties(movie);
        assertEquals(76726, movie.getId());
    }

    /**
     * Test {@link TmdbAccount#getWatchListTvSeries(Integer, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetWatchListTvSeries() throws TmdbException, IOException {
        Integer accountId = 1234;
        String sessionId = "testSessionId";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("api_responses/account/watchlist_tv.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        TvSeriesResultsPage tvSeriesResultsPage = tmdbAccount.getWatchListTvSeries(accountId, sessionId, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        testForNullFieldsAndUnknownProperties(tvSeriesResultsPage);
        assertEquals(1, tvSeriesResultsPage.getPage());
        assertEquals(17, tvSeriesResultsPage.getTotalPages());
        assertEquals(325, tvSeriesResultsPage.getTotalResults());

        List<TvSeries> tvSeriess = tvSeriesResultsPage.getResults();
        assertEquals(20, tvSeriess.size());

        TvSeries tvSeries = tvSeriess.get(0);
        testForNullFieldsAndUnknownProperties(tvSeries);
        assertEquals(58932, tvSeries.getId());
    }
}
