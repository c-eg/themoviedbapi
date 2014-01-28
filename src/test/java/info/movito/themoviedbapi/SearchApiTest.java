package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Collection;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.Person;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SearchApiTest extends AbstractTmdbApiTest {


    @Test
    public void testSearchPeople() {
        String personName = "Bruce Willis";
        boolean includeAdult = false;
        List<Person> result = tmdb.getSearch().searchPeople(personName, includeAdult, 0);
        assertTrue("Couldn't find the person", result.size() > 0);
    }


    @Test
    public void testSearchList() throws Exception {
        List<MovieList> results = tmdb.getSearch().searchList("watch", LANGUAGE_DEFAULT, 0);

        assertFalse("No lists found", results == null);
        assertTrue("No lists found", results.size() > 0);
    }


    @Test
    public void testSearchKeyword() throws Exception {
        List<Keyword> results = tmdb.getSearch().searchKeyword("action", 0);

        assertFalse("No keywords found", results == null);
        assertTrue("No keywords found", results.size() > 0);
    }


    @Test
    public void testSearchCollection() throws Exception {
        List<Collection> result = tmdb.getSearch().searchCollection("batman", LANGUAGE_DEFAULT, 0);

        assertFalse("No collections found", result == null);
        assertTrue("No collections found", result.size() > 0);
    }


    @Test
    public void testSearchMovie() {
        // Try a movie with less than 1 page of results
        TmdbSearch seach = tmdb.getSearch();

        List<MovieDb> movieList = seach.searchMovie("Blade Runner", 0, "", true, 0);
//        List<MovieDb> movieList = tmdb.searchMovie("Blade Runner", "", true);
        assertTrue("No movies found, should be at least 1", movieList.size() > 0);

        // Try a russian langugage movie
        movieList = tmdb.getSearch().searchMovie("О чём говорят мужчины", 0, LANGUAGE_RUSSIAN, true, 0);
        assertTrue("No 'RU' movies found, should be at least 1", movieList.size() > 0);

        // Try a movie with more than 20 results
        movieList = tmdb.getSearch().searchMovie("Star Wars", 0, LANGUAGE_ENGLISH, false, 0);
        assertTrue("Not enough movies found, should be over 15, found " + movieList.size(), movieList.size() >= 15);
    }


    @Test
    public void testSearchCompanies() {
        List<Company> result = tmdb.getSearch().searchCompanies(COMPANY_NAME, 0);
        assertTrue("No company information found", !result.isEmpty());
    }
}
