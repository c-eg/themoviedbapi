package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.*;


public class AccountApiTest extends AbstractTmdbApiTest {


    // protected fields to ensure usage just for testing
    static final AccountID APITESTS_ACCOUNT = new AccountID(6065849);
    static final SessionToken APITESTS_TOKEN = new SessionToken("76c5c544e9c1f51d7569989d95a8d10cfb5164e5");


    @Test
    public void testAccount() {
        Account account = tmdb.getAccount().getAccount(APITESTS_TOKEN);

        // Make sure properties are extracted correctly
        assertEquals(account.getUserName(), "apitests");
        assertEquals(account.getId() + "", APITESTS_ACCOUNT.toString());
    }


    @Test
    public void testGetMovieLists() throws Exception {
        TmdbMovies tmdbMovies = tmdb.getMovies();
        List<MovieList> result = tmdbMovies.getListsContaining(ID_MOVIE_BLADE_RUNNER,
                APITESTS_TOKEN, LANGUAGE_ENGLISH, 0).getResults();

        assertNotNull("No results found", result);
        assertTrue("No results found", result.size() > 0);
    }


    @Test
    public void testWatchList() {
        // make sure it's empty (because it's just a test account
        TmdbAccount account = tmdb.getAccount();

        Assert.assertTrue(account.getWatchListMovies(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults().isEmpty());
        Assert.assertTrue(account.getWatchListSeries(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults().isEmpty());

        // add a movie
        account.addToWatchList(APITESTS_TOKEN, APITESTS_ACCOUNT, 550, TmdbAccount.MediaType.MOVIE);

        // add a tv series
        account.addToWatchList(APITESTS_TOKEN, APITESTS_ACCOUNT, 1396, TmdbAccount.MediaType.TV);

        List<MovieDb> watchlistMovies = account.getWatchListMovies(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults();
        assertTrue(watchlistMovies.size() == 1);

        List<TvSeries> watchlistSeries = account.getWatchListSeries(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults();
        assertTrue(watchlistSeries.size() == 1);

        // clean up again
        account.removeFromWatchList(APITESTS_TOKEN, APITESTS_ACCOUNT, 1396, TmdbAccount.MediaType.TV);
        account.removeFromWatchList(APITESTS_TOKEN, APITESTS_ACCOUNT, 550, TmdbAccount.MediaType.MOVIE);

        Assert.assertTrue(account.getWatchListMovies(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults().isEmpty());
    }


    @Test
    public void testFavorites() {
        // make sure it's empty (because it's just a test account
        TmdbAccount account = tmdb.getAccount();

        Assert.assertTrue(account.getFavoriteMovies(APITESTS_TOKEN, APITESTS_ACCOUNT).getResults().isEmpty());

        // add a movie
        account.addFavorite(APITESTS_TOKEN, APITESTS_ACCOUNT, 550, TmdbAccount.MediaType.MOVIE);

        List<MovieDb> favoriteMovies = account.getFavoriteMovies(APITESTS_TOKEN, APITESTS_ACCOUNT).getResults();
        assertTrue(favoriteMovies.size() == 1);

        // clean up again
        account.removeFavorite(APITESTS_TOKEN, APITESTS_ACCOUNT, 550, TmdbAccount.MediaType.MOVIE);

        Assert.assertTrue(account.getFavoriteMovies(APITESTS_TOKEN, APITESTS_ACCOUNT).getResults().isEmpty());
    }


    @Test
    public void testMovieRating() throws Exception {
        Integer movieID = 68724;
        Integer rating = new Random().nextInt(10) + 1;

        boolean wasPosted = tmdb.getAccount().postMovieRating(APITESTS_TOKEN, movieID, rating);

        assertTrue(wasPosted);

        // get all rated movies
        Thread.sleep(2000);

        List<MovieDb> ratedMovies = tmdb.getAccount().getRatedMovies(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults();
        assertTrue(ratedMovies.size() > 0);

        // make sure that we find the movie and it is rated correctly
        boolean foundMovie = false;
        for (MovieDb movie : ratedMovies) {
            if (movie.getId() == movieID) {
                assertEquals(movie.getUserRating(), (float) rating, 0);
                foundMovie = true;
            }
        }
        assertTrue(foundMovie);
    }


    @Test
    public void testSeriesRating() throws Exception {
        Integer seriesId = 57243; // dr who
        Integer rating = new Random().nextInt(10) + 1;

        boolean wasPosted = tmdb.getAccount().postTvSeriesRating(APITESTS_TOKEN, seriesId, rating);

        assertTrue(wasPosted);

        // get all rated movies
        Thread.sleep(1000);

        List<TvSeries> ratedSeries = tmdb.getAccount().getRatedTvSeries(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults();
        assertTrue(ratedSeries.size() > 0);

        // make sure that we find the movie and it is rated correctly
        boolean foundSeries = false;
        for (TvSeries series : ratedSeries) {
            if (series.getId() == seriesId) {
                assertEquals(series.getUserRating(), (float) rating, 0);
                foundSeries = true;
            }
        }
        assertTrue(foundSeries);
    }


    @Test
    public void testEpisodeRating() throws Exception {
        // https://www.themoviedb.org/tv/57243-doctor-who/season/7/episode/8
        Integer seriesId = 57243; // dr who
        Integer seasonNr = 7; // dr who
        Integer episodeNr = 8; // dr who

        Integer rating = new Random().nextInt(10) + 1;

        boolean wasPosted = tmdb.getAccount().postTvExpisodeRating(APITESTS_TOKEN, seriesId, seasonNr, episodeNr, rating);

        assertTrue(wasPosted);
        Thread.sleep(1000);   // test fails without because there seems to be some delay

        List<TvEpisode> ratedEpisodes = tmdb.getAccount().getRatedEpisodes(APITESTS_TOKEN, APITESTS_ACCOUNT, null).getResults();
        assertTrue(ratedEpisodes.size() > 0);

        // make sure that we find the movie and it is rated correctly
        boolean foundSeries = false;
        for (TvEpisode episode : ratedEpisodes) {
            if (Objects.equals(episode.getSeriesId(), seriesId)) {
                assertEquals(episode.getUserRating(), (float) rating, 0);
                foundSeries = true;
            }
        }
        assertTrue(foundSeries);
    }


    @Test
    public void listUserLists() {
        List<MovieList> lists = tmdb.getAccount().getLists(APITESTS_TOKEN, APITESTS_ACCOUNT, null, null).getResults();

        Assert.assertNotNull(lists);

        //todo do something more elaborate here!
    }

}
