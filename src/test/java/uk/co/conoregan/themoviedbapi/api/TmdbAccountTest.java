package uk.co.conoregan.themoviedbapi.api;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.TestUtils;
import uk.co.conoregan.themoviedbapi.model.MovieList;
import uk.co.conoregan.themoviedbapi.model.MovieListResultsPage;
import uk.co.conoregan.themoviedbapi.model.account.Movie;
import uk.co.conoregan.themoviedbapi.model.account.MovieResultsPage;
import uk.co.conoregan.themoviedbapi.model.account.TvSeries;
import uk.co.conoregan.themoviedbapi.model.account.TvSeriesResultsPage;
import uk.co.conoregan.themoviedbapi.model.config.Account;
import uk.co.conoregan.themoviedbapi.model.core.AccountID;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.model.rated.RatedMovie;
import uk.co.conoregan.themoviedbapi.model.rated.RatedMovieResultsPage;
import uk.co.conoregan.themoviedbapi.model.rated.RatedTvEpisode;
import uk.co.conoregan.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import uk.co.conoregan.themoviedbapi.model.rated.RatedTvSeries;
import uk.co.conoregan.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import uk.co.conoregan.themoviedbapi.tools.SortBy;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link uk.co.conoregan.themoviedbapi.api.TmdbAccount}.
 */
public class TmdbAccountTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbAccount#getDetails(String)} with an expected result.
     */
    @Test
    public void testGetAccount() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("account/details.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        Account account = tmdbAccount.getDetails("testSessionToken");
        assertNotNull(account);
        assertTrue(account.getUnknownProperties().isEmpty());  // check for no unknown properties

        assertEquals("c9e9fc152ee756a900db85757c29815d", account.getAvatar().getGravatarHash());
        assertEquals("/xy44UvpbTgzs9kWmp4C3fEaCl5h.png", account.getAvatar().getTmdbAvatarPath());
        assertEquals(548, account.getId());
        assertEquals("en", account.getIso6391());
        assertEquals("CA", account.getIso31661());
        assertEquals("Travis Bell", account.getName());
        assertFalse(account.isIncludeAdult());
        assertEquals("travisbell", account.getUsername());
    }

    /**
     * Test {@link TmdbAccount#addFavorite(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddFavourite() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("account/add_favourite.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.addFavorite(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#removeFavorite(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFavorite() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("account/add_favourite.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.removeFavorite(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#addToWatchList(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testAddToWatchList() throws IOException, TmdbException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("account/add_to_watchlist.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.addToWatchList(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#removeFromWatchList(AccountID, String, Integer, TmdbAccount.MediaType)} with an expected result.
     */
    @Test
    public void testRemoveFromWatchList() throws IOException, TmdbException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer mediaId = 1234;
        TmdbAccount.MediaType mediaType = TmdbAccount.MediaType.MOVIE;

        String body = TestUtils.readTestFile("account/add_to_watchlist.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        ResponseStatus responseStatus = tmdbAccount.removeFromWatchList(accountId, sessionToken, mediaId, mediaType);
        assertNotNull(responseStatus);
        assertTrue(responseStatus.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, responseStatus.getStatusCode());
        assertEquals("Success.", responseStatus.getStatusMessage());
    }

    /**
     * Test {@link TmdbAccount#getFavoriteMovies(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetFavouriteMovies() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/favourite_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        MovieResultsPage movieResultsPage = tmdbAccount.getFavoriteMovies(accountId, sessionToken, language, page, sortBy);
        assertNotNull(movieResultsPage);
        assertTrue(movieResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, movieResultsPage.getPage());
        assertEquals(4, movieResultsPage.getTotalPages());
        assertEquals(80, movieResultsPage.getTotalResults());

        List<Movie> movieResults = movieResultsPage.getResults();
        assertEquals(20, movieResults.size());

        Movie movie = movieResults.get(0);
        assertTrue(movie.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(9806, movie.getId());
    }

    /**
     * Test {@link TmdbAccount#getFavoriteTv(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetFavouriteTv() throws IOException, TmdbException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/favourite_tv.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        TvSeriesResultsPage tvSeriesResultsPage = tmdbAccount.getFavoriteTv(accountId, sessionToken, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        assertTrue(tvSeriesResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, tvSeriesResultsPage.getPage());
        assertEquals(4, tvSeriesResultsPage.getTotalPages());
        assertEquals(68, tvSeriesResultsPage.getTotalResults());

        List<TvSeries> tvResults = tvSeriesResultsPage.getResults();
        assertEquals(20, tvResults.size());

        TvSeries tvSeries = tvResults.get(0);
        assertTrue(tvSeries.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1396, tvSeries.getId());
    }

    /**
     * Test {@link TmdbAccount#getLists(AccountID, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        Integer page = 1;

        String body = TestUtils.readTestFile("account/lists.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        MovieListResultsPage movieListResultsPage = tmdbAccount.getLists(accountId, sessionToken, page);
        assertNotNull(movieListResultsPage);
        assertTrue(movieListResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, movieListResultsPage.getPage());
        assertEquals(2, movieListResultsPage.getTotalPages());
        assertEquals(25, movieListResultsPage.getTotalResults());

        List<MovieList> movieLists = movieListResultsPage.getResults();
        assertEquals(20, movieLists.size());

        MovieList movieList = movieLists.get(0);
        assertTrue(movieList.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(120174, movieList.getId());
    }

    /**
     * Test {@link TmdbAccount#getRatedMovies(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetRatedMovies() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/rated_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        RatedMovieResultsPage ratedMovieResultsPage = tmdbAccount.getRatedMovies(accountId, sessionToken, language, page, sortBy);
        assertNotNull(ratedMovieResultsPage);
        assertTrue(ratedMovieResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, ratedMovieResultsPage.getPage());
        assertEquals(47, ratedMovieResultsPage.getTotalPages());
        assertEquals(940, ratedMovieResultsPage.getTotalResults());

        List<RatedMovie> ratedMovies = ratedMovieResultsPage.getResults();
        assertEquals(16, ratedMovies.size());

        RatedMovie ratedMovie = ratedMovies.get(0);
        assertTrue(ratedMovie.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(120, ratedMovie.getId());
    }

    /**
     * Test {@link TmdbAccount#getRatedTvSeries(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvSeries() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/rated_tv.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        RatedTvSeriesResultsPage ratedTvSeriesResultsPage = tmdbAccount.getRatedTvSeries(accountId, sessionToken, language, page, sortBy);
        assertNotNull(ratedTvSeriesResultsPage);
        assertTrue(ratedTvSeriesResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, ratedTvSeriesResultsPage.getPage());
        assertEquals(15, ratedTvSeriesResultsPage.getTotalPages());
        assertEquals(290, ratedTvSeriesResultsPage.getTotalResults());

        List<RatedTvSeries> ratedTvSeriess = ratedTvSeriesResultsPage.getResults();
        assertEquals(20, ratedTvSeriess.size());

        RatedTvSeries ratedTvSeries = ratedTvSeriess.get(0);
        assertTrue(ratedTvSeries.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1705, ratedTvSeries.getId());
    }

    /**
     * Test {@link TmdbAccount#getRatedTvEpisodes(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetRatedTvEpisodes() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/rated_tv_episodes.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        RatedTvEpisodeResultsPage ratedTvEpisodesResultsPage = tmdbAccount.getRatedTvEpisodes(accountId, sessionToken, language, page, sortBy);
        assertNotNull(ratedTvEpisodesResultsPage);
        assertTrue(ratedTvEpisodesResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, ratedTvEpisodesResultsPage.getPage());
        assertEquals(10, ratedTvEpisodesResultsPage.getTotalPages());
        assertEquals(186, ratedTvEpisodesResultsPage.getTotalResults());

        List<RatedTvEpisode> ratedTvEpisodes = ratedTvEpisodesResultsPage.getResults();
        assertEquals(20, ratedTvEpisodes.size());

        RatedTvEpisode ratedTvEpisode = ratedTvEpisodes.get(0);
        assertTrue(ratedTvEpisode.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(64782, ratedTvEpisode.getId());
    }

    /**
     * Test {@link TmdbAccount#getWatchListMovies(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetWatchListMovies() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/watchlist_movies.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        MovieResultsPage movieResultsPage = tmdbAccount.getWatchListMovies(accountId, sessionToken, language, page, sortBy);
        assertNotNull(movieResultsPage);
        assertTrue(movieResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, movieResultsPage.getPage());
        assertEquals(34, movieResultsPage.getTotalPages());
        assertEquals(677, movieResultsPage.getTotalResults());

        List<Movie> movies = movieResultsPage.getResults();
        assertEquals(20, movies.size());

        Movie movie = movies.get(0);
        assertTrue(movie.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(76726, movie.getId());
    }

    /**
     * Test {@link TmdbAccount#getWatchListTvSeries(AccountID, String, String, Integer, SortBy)} with an expected result.
     */
    @Test
    public void testGetWatchListTvSeries() throws TmdbException, IOException {
        AccountID accountId = new AccountID(1234);
        String sessionToken = "testSessionToken";
        String language = "en";
        Integer page = 1;
        SortBy sortBy = SortBy.ASC;

        String body = TestUtils.readTestFile("account/watchlist_tv.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = new TmdbAccount(getTmdbApi());
        TvSeriesResultsPage tvSeriesResultsPage = tmdbAccount.getWatchListTvSeries(accountId, sessionToken, language, page, sortBy);
        assertNotNull(tvSeriesResultsPage);
        assertTrue(tvSeriesResultsPage.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(1, tvSeriesResultsPage.getPage());
        assertEquals(17, tvSeriesResultsPage.getTotalPages());
        assertEquals(325, tvSeriesResultsPage.getTotalResults());

        List<TvSeries> tvSeriess = tvSeriesResultsPage.getResults();
        assertEquals(20, tvSeriess.size());

        TvSeries tvSeries = tvSeriess.get(0);
        assertTrue(tvSeries.getUnknownProperties().isEmpty());  // check for no unknown properties
        assertEquals(58932, tvSeries.getId());
    }
}
