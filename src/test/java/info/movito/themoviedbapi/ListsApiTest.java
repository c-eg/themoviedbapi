package info.movito.themoviedbapi;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.core.StatusCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static info.movito.themoviedbapi.AccountApiTest.APITESTS_ACCOUNT;
import static info.movito.themoviedbapi.AccountApiTest.APITESTS_TOKEN;
import static org.junit.Assert.*;


public class ListsApiTest extends AbstractTmdbApiTest {

    @Test
    public void getList() {

        TmdbLists listsApi = tmdb.getLists();

//        http://www.themoviedb.org/list/5414325f0e0a26199b001989
        MovieList list = listsApi.getList("5414325f0e0a26199b001989");
        Assert.assertTrue(list.getItemCount() > 20);
        Assert.assertTrue(list.getItems().size() > 20);
    }


    @Test
    public void listManagement() {

        TmdbLists listApi = tmdb.getLists();
        TmdbAccount accountApi = tmdb.getAccount();

        String listName = "Test List";
        String listDesc = "blaMovieList bla bla";


        // create a list
        final String listId = listApi.createList(APITESTS_TOKEN, listName, listDesc);

        Assert.assertTrue(listId != null && listId.length() > 10);

        // ... and add a movie
        listApi.addMovieToList(APITESTS_TOKEN, listId, ID_MOVIE_BLADE_RUNNER);


        // check if the list is there and contains blade runner
        MovieList result = tmdb.getLists().getList(listId);

        assertNotNull("List not found", result);
        assertFalse("List not found", result.getItems().size() == 1);
        assertEquals(ID_MOVIE_BLADE_RUNNER, result.getItems().get(0).getId());


        // list all lists to see if ours is there as well
        List<MovieList> lists = accountApi.getLists(APITESTS_TOKEN, APITESTS_ACCOUNT, null, null);


        // might not be true because of other test lists
        Assert.assertFalse(lists.isEmpty());

        MovieList movieList = Iterables.find(lists, new Predicate<MovieList>() {
            @Override
            public boolean apply(MovieList movieList) {
                return movieList.getId().equals(listId);
            }
        });

        Assert.assertNotNull(movieList);
        Assert.assertEquals(listName, movieList.getName());
        Assert.assertEquals(listDesc, movieList.getDescription());


        // get rid of it
        listApi.deleteMovieList(APITESTS_TOKEN, listId);

        Assert.assertNull(listApi.getList(listId));
    }


//    @Test
//    public void testGetList() throws Exception {
//        String listId = "509ec17b19c2950a0600050d";
//        MovieList result = tmdb.getLists().getList(listId);
//
//        assertFalse("List not found", result.getItems().isEmpty());
//    }


    @Test
    public void testMovieLists() throws Exception {
        Integer movieID = 68724;

        // use a random name to avoid that we clash we leftovers of incomplete test runs
        String name = "test list " + new Random().nextInt(100);

        // create the list
        TmdbLists tmdbLists = tmdb.getLists();
        String listId = tmdbLists.createList(APITESTS_TOKEN, name, "api testing only");

        // add a movie, and test that it is on the list now
        tmdbLists.addMovieToList(APITESTS_TOKEN, listId, movieID);
        MovieList list = tmdbLists.getList(listId);
        Assert.assertEquals(list.getItemCount(), 1);
        assertEquals(list.getItems().get(0).getId(), (int) movieID);

        // now remove the movie
        tmdbLists.removeMovieFromList(APITESTS_TOKEN, listId, movieID);
        Assert.assertEquals(tmdbLists.getList(listId).getItemCount(), 0);

        // delete the test list
        StatusCode statusCode = tmdbLists.deleteMovieList(APITESTS_TOKEN, listId);
        assertEquals(statusCode.getStatusCode(), 13);
    }



}
