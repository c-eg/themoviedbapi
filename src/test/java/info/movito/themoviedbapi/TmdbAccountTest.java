package info.movito.themoviedbapi;

import org.junit.jupiter.api.Test;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.MovieListResultsPage;
import info.movito.themoviedbapi.model.account.Movie;
import info.movito.themoviedbapi.model.account.MovieResultsPage;
import info.movito.themoviedbapi.model.account.TvSeries;
import info.movito.themoviedbapi.model.account.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.account.Account;
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
     * Test {@link TmdbAccount#getDetails(Integer, String)} with an expected result.
     */
    @Test
    public void testGetAccount() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/account/details.json");
        mockResponse(body, 200);

        TmdbAccount tmdbAccount = getTmdbApi().getAccount();
        Account account = tmdbAccount.getDetails(1234, "testSessionId");
        assertNotNull(account);
        testForNullFieldsAndUnknownProperties(account);
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

        List<Movie> movieResults = movieResultsPage.getResults();
        assertFalse(movieResults.isEmpty());

        Movie movie = movieResults.get(0);
        assertNotNull(movie);
        testForNullFieldsAndUnknownProperties(movie);
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

        List<TvSeries> tvResults = tvSeriesResultsPage.getResults();
        assertFalse(tvResults.isEmpty());

        TvSeries tvSeries = tvResults.get(0);
        assertNotNull(tvSeries);
        testForNullFieldsAndUnknownProperties(tvSeries);
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

        List<MovieList> movieLists = movieListResultsPage.getResults();
        assertFalse(movieLists.isEmpty());

        MovieList movieList = movieLists.get(0);
        assertNotNull(movieList);
        testForNullFieldsAndUnknownProperties(movieList);
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

        List<RatedMovie> ratedMovies = ratedMovieResultsPage.getResults();
        assertFalse(ratedMovies.isEmpty());

        RatedMovie ratedMovie = ratedMovies.get(0);
        assertNotNull(ratedMovie);
        testForNullFieldsAndUnknownProperties(ratedMovie);
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

        List<RatedTvSeries> ratedTvSeriess = ratedTvSeriesResultsPage.getResults();
        assertFalse(ratedTvSeriess.isEmpty());

        RatedTvSeries ratedTvSeries = ratedTvSeriess.get(0);
        assertNotNull(ratedTvSeries);
        testForNullFieldsAndUnknownProperties(ratedTvSeries);
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

        List<RatedTvEpisode> ratedTvEpisodes = ratedTvEpisodesResultsPage.getResults();
        assertFalse(ratedTvEpisodes.isEmpty());

        RatedTvEpisode ratedTvEpisode = ratedTvEpisodes.get(0);
        assertNotNull(ratedTvEpisode);
        testForNullFieldsAndUnknownProperties(ratedTvEpisode);
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

        List<Movie> movies = movieResultsPage.getResults();
        assertFalse(movies.isEmpty());

        Movie movie = movies.get(0);
        assertNotNull(movie);
        testForNullFieldsAndUnknownProperties(movie);
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

        List<TvSeries> tvSeriess = tvSeriesResultsPage.getResults();
        assertFalse(tvSeriess.isEmpty());

        TvSeries tvSeries = tvSeriess.get(0);
        assertNotNull(tvSeries);
        testForNullFieldsAndUnknownProperties(tvSeries);
    }
}
