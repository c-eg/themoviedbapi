package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.model.core.ResponseStatusException;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.tools.WebBrowser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class MiscApiTest extends AbstractTmdbApiTest {


    @Test
    public void testConfiguration() throws IOException {

        TmdbConfiguration tmdbConfig = tmdb.getConfiguration();
        assertNotNull("Configuration failed", tmdbConfig);
        assertTrue("No base URL", StringUtils.isNotBlank(tmdbConfig.getBaseUrl()));
        assertTrue("No backdrop sizes", tmdbConfig.getBackdropSizes().size() > 0);
        assertTrue("No poster sizes", tmdbConfig.getPosterSizes().size() > 0);
        assertTrue("No profile sizes", tmdbConfig.getProfileSizes().size() > 0);
    }


    //
    // Request Limit Handling
    //


    @Test
    public void limitPush() {
        for (int i = 0; i < 50; i++) {
            try {
                tmdb.getMovies().getMovie(ID_MOVIE_BLADE_RUNNER, LANGUAGE_ENGLISH);
            } catch (Exception e) {
                Assert.fail();
            }
        }
    }


    @Test
    public void limitPushWithoutAutoRety() {
        // wait before and after to
//        Utils.sleep(10000);

        TmdbApi tmdb = new TmdbApi(getApiKey(), new WebBrowser(), false);


        for (int i = 0; i < 50; i++) {
            try {
                tmdb.getMovies().getMovie(ID_MOVIE_BLADE_RUNNER, LANGUAGE_ENGLISH);
            } catch (ResponseStatusException e) {
                Assert.assertEquals(25, e.getResponseStatus().getStatusCode().intValue());
                return;
            }
        }


        Assert.fail("failed to throw response status because of api limit");

//        Utils.sleep(10000);
    }


    //
    // JOBS
    //


    @Test
    public void testGetJobs() throws Exception {

        List<JobDepartment> result = tmdb.getJobs();
        assertFalse("No jobs found", result.isEmpty());
    }


    //
    // DISCOVER
    //


    @Test
    public void testDiscoverByYear() throws Exception {

        Discover discover = new Discover();
        discover.year(2013).language(LANGUAGE_ENGLISH);

        List<MovieDb> result = tmdb.getDiscover().getDiscover(discover).getResults();
        assertFalse("No movies discovered", result.isEmpty());
    }


    @Test
    public void testDiscoverByKeyword() throws Exception {
        Keyword keyword = tmdb.getKeywords().getKeyword(ID_KEYWORD);

        Discover discover = new Discover();
        discover.withKeywords(Collections.singletonList(keyword), false).language(LANGUAGE_ENGLISH);

        List<MovieDb> result = tmdb.getDiscover().getDiscover(discover).getResults();
        assertFalse("No movies discovered", result.isEmpty());
    }


    //
    // CHANGES
    //


    @Ignore("Not ready yet")
    public void testGetMovieChangesList() throws Exception {

        int page = 0;
        String startDate = "";
        String endDate = "";
        tmdb.getChanges().getMovieChangesList(page, startDate, endDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Ignore("Not ready yet")
    public void testGetPersonChangesList() throws Exception {

        int page = 0;
        String startDate = "";
        String endDate = "";
        tmdb.getChanges().getPersonChangesList(page, startDate, endDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    //
    // KEYWORDS
    //


    @Test
    public void testGetKeyword() throws Exception {

        Keyword result = tmdb.getKeywords().getKeyword(ID_KEYWORD);
        assertEquals("fight", result.getName());
    }


    @Test
    public void testGetKeywordMovies() throws Exception {
        List<MovieDb> result = tmdb.getKeywords().getKeywordMovies(ID_KEYWORD, LANGUAGE_DEFAULT, 0).getResults();
        assertFalse("No keyword movies found", result.isEmpty());
    }


    //
    // REVIEWS
    //


    @Test
    public void testGetReviews() throws Exception {
        List<Reviews> result = tmdb.getReviews().getReviews(ID_MOVIE_THE_AVENGERS, LANGUAGE_DEFAULT, 0).getResults();

        assertFalse("No reviews found", result.isEmpty());
    }


    //
    // GENRE
    //


    @Test
    public void testGetGenreList() {
        List<Genre> result = tmdb.getGenre().getGenreList(LANGUAGE_DEFAULT);

        assertTrue("No genres found", !result.isEmpty());
    }


    @Test
    public void testGetGenreMovies() {
        List<MovieDb> result = tmdb.getGenre().getGenreMovies(ID_GENRE_ACTION, LANGUAGE_DEFAULT, 0, Boolean.TRUE).getResults();

        assertTrue("No genre movies found", !result.isEmpty());
    }
}
