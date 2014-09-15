package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


public class AccountApiTest extends AbstractTmdbApiTest {


    // protected fields to ensure usage just for testing
    static final int ACCOUNT_ID_APITESTS = 6065849;
    static final SessionToken SESSION_ID = new SessionToken("76c5c544e9c1f51d7569989d95a8d10cfb5164e5");


    @Test
    public void testAccount() {
        Account account = tmdb.getAccount().getAccount(SESSION_ID);

        // Make sure properties are extracted correctly
        assertEquals(account.getUserName(), "apitests");
        assertEquals(account.getId(), ACCOUNT_ID_APITESTS);
    }


    @Test
    public void testGetMovieLists() throws Exception {
        List<MovieList> result = tmdb.getMovies().getListsContaining(ID_MOVIE_BLADE_RUNNER, SESSION_ID, LANGUAGE_ENGLISH, 0);

        assertNotNull("No results found", result);
        assertTrue("No results found", result.size() > 0);
    }


    @Test
    public void testWatchList() {
        // make sure it's empty (because it's just a test account
        TmdbAccount account = tmdb.getAccount();

        Assert.assertTrue(account.getWatchListMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults().isEmpty());
        Assert.assertTrue(account.getWatchListSeries(SESSION_ID, ACCOUNT_ID_APITESTS).getResults().isEmpty());

        // add a movie
        account.addToWatchList(SESSION_ID, ACCOUNT_ID_APITESTS, 550, TmdbAccount.MediaType.MOVIE);

        // add a tv series
        account.addToWatchList(SESSION_ID, ACCOUNT_ID_APITESTS, 1396, TmdbAccount.MediaType.TV);

        List<MovieDb> watchlistMovies = account.getWatchListMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults();
        assertTrue(watchlistMovies.size() == 1);

        List<TvSeries> watchlistSeries = account.getWatchListSeries(SESSION_ID, ACCOUNT_ID_APITESTS).getResults();
        assertTrue(watchlistSeries.size() == 1);

        // clean up again
        account.removeFromWatchList(SESSION_ID, ACCOUNT_ID_APITESTS, 1396, TmdbAccount.MediaType.TV);
        account.removeFromWatchList(SESSION_ID, ACCOUNT_ID_APITESTS, 550, TmdbAccount.MediaType.MOVIE);

        Assert.assertTrue(account.getWatchListMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults().isEmpty());
    }


    @Test
    public void testFavorites() {
        // make sure it's empty (because it's just a test account
        TmdbAccount account = tmdb.getAccount();

        Assert.assertTrue(account.getFavoriteMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults().isEmpty());

        // add a movie
        account.changeFavoriteStatus(SESSION_ID, ACCOUNT_ID_APITESTS, 550, TmdbAccount.MediaType.MOVIE, true);

        List<MovieDb> favoriteMovies = account.getFavoriteMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults();
        assertTrue(favoriteMovies.size() == 1);

        // clean up again
        account.changeFavoriteStatus(SESSION_ID, ACCOUNT_ID_APITESTS, 550, TmdbAccount.MediaType.MOVIE, false);

        Assert.assertTrue(account.getFavoriteMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults().isEmpty());
    }


    @Test
    public void testMovieRating() throws Exception {

        Integer movieID = 68724;
        Integer rating = new Random().nextInt(10) + 1;

        boolean wasPosted = tmdb.getAccount().postMovieRating(SESSION_ID, movieID, rating);

        assertTrue(wasPosted);

        // get all rated movies
        Thread.sleep(2000);

        List<MovieDb> ratedMovies = tmdb.getAccount().getRatedMovies(SESSION_ID, ACCOUNT_ID_APITESTS).getResults();
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


}
