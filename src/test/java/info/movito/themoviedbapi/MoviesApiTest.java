package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.changes.ChangesItems;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.Person;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class MoviesApiTest extends AbstractTmdbApiTest {


    @Test
    public void testGetMovieInfo() {
        MovieDb result = tmdb.getMovies().getMovieInfo(ID_MOVIE_BLADE_RUNNER, LANGUAGE_ENGLISH);
        assertEquals("Incorrect movie information", "Blade Runner", result.getOriginalTitle());
    }


    @Test
    public void testGetMovieInfoWithAppendedMethods() {
        MovieDb result = tmdb.getMovies().getMovieInfo(ID_MOVIE_BLADE_RUNNER, LANGUAGE_ENGLISH, TmdbMovies.MovieMethod.values());
        assertEquals("Incorrect movie information", "Blade Runner", result.getOriginalTitle());
    }


    @Test
    public void testGetMovieAlternativeTitles() {
        String country = "";


        List<AlternativeTitle> result = tmdb.getMovies().getMovieAlternativeTitles(ID_MOVIE_BLADE_RUNNER, country);
        assertTrue("No alternative titles found", result.size() > 0);

        country = "US";
        result = tmdb.getMovies().getMovieAlternativeTitles(ID_MOVIE_BLADE_RUNNER, country);
        assertTrue("No alternative titles found", result.size() > 0);

    }


    @Test
    public void testGetMovieCasts() {
        Credits people = tmdb.getMovies().getCredits(ID_MOVIE_BLADE_RUNNER);
        assertTrue("No cast information", people.getAll().size() > 0);

        String name1 = "Harrison Ford";
        String name2 = "Charles Knode";

        boolean foundName1 = Boolean.FALSE;
        boolean foundName2 = Boolean.FALSE;

        for (Person person : people.getAll()) {
            if (!foundName1 && person.getName().equalsIgnoreCase(name1)) {
                foundName1 = Boolean.TRUE;
            }

            if (!foundName2 && person.getName().equalsIgnoreCase(name2)) {
                foundName2 = Boolean.TRUE;
            }
        }
        assertTrue("Couldn't find " + name1, foundName1);
        assertTrue("Couldn't find " + name2, foundName2);
    }


    @Test
    public void testGetMovieImages() {
        String language = "";
        MovieImages result = tmdb.getMovies().getImages(ID_MOVIE_BLADE_RUNNER, language);
        assertFalse("No artwork found", result.getPosters().isEmpty());
    }


    @Test
    public void testGetMovieKeywords() {
        List<Keyword> result = tmdb.getMovies().getMovieKeywords(ID_MOVIE_BLADE_RUNNER);
        assertFalse("No keywords found", result.isEmpty());
    }


    @Test
    public void testMovieKeywordsWithHits() {
        List<Keyword> result = tmdb.getMovies().getMovieKeywords(550);
        assertFalse("No keywords found", result.isEmpty());
    }


    @Test
    public void testGetMovieReleaseInfo() {
        List<ReleaseInfo> result = tmdb.getMovies().getMovieReleaseInfo(ID_MOVIE_BLADE_RUNNER, "");
        assertFalse("Release information missing", result.isEmpty());
    }


    @Test
    public void testGetMovieTrailers() {
        List<Trailer> result = tmdb.getMovies().getMovieTrailers(ID_MOVIE_BLADE_RUNNER, "");
        assertFalse("Movie trailers missing", result.isEmpty());
    }


    @Test
    public void testGetMovieTranslations() {
        List<Translation> result = tmdb.getMovies().getMovieTranslations(ID_MOVIE_BLADE_RUNNER);
        assertFalse("No translations found", result.isEmpty());
    }


    @Test
    public void testCreateImageUrl() {
        MovieDb movie = tmdb.getMovies().getMovieInfo(ID_MOVIE_BLADE_RUNNER, "");
        String result = Utils.createImageUrl(tmdb, movie.getPosterPath(), "original").toString();
        assertTrue("Error compiling image URL", !result.isEmpty());
    }


    @Test
    public void testGetNowPlayingMovies() {
        List<MovieDb> result = tmdb.getMovies().getNowPlayingMovies(LANGUAGE_DEFAULT, 0);
        assertTrue("No now playing movies found", !result.isEmpty());
    }


    @Test
    public void testGetPopularMovieList() {
        List<MovieDb> result = tmdb.getMovies().getPopularMovieList(LANGUAGE_DEFAULT, 0);
        assertTrue("No popular movies found", !result.isEmpty());
    }


    @Test
    public void testGetTopRatedMovies() {
        List<MovieDb> results = tmdb.getMovies().getTopRatedMovies(LANGUAGE_DEFAULT, 0);
        assertTrue("No top rated movies found", !results.isEmpty());
    }


    @Test
    public void testGetSimilarMovies() {
        List<MovieDb> result = tmdb.getMovies().getSimilarMovies(ID_MOVIE_BLADE_RUNNER, LANGUAGE_DEFAULT, 0);
        assertTrue("No similar movies found", !result.isEmpty());
    }


    @Test
    public void testGetLatestMovie() {
        MovieDb result = tmdb.getMovies().getLatestMovie();
        assertTrue("No latest movie found", result != null);
        assertTrue("No latest movie found", result.getId() > 0);
    }


    @Test
    public void testGetUpcoming() throws Exception {

        List<MovieDb> result = tmdb.getMovies().getUpcoming(LANGUAGE_DEFAULT, 0);
        assertTrue("No upcoming movies found", !result.isEmpty());
    }


    @Ignore("Do not test this until it is fixed")
    public void testGetMovieChanges() throws Exception {
        String startDate = "";
        String endDate = null;

        // Get some popular movies
        List<MovieDb> movieList = tmdb.getMovies().getPopularMovieList(LANGUAGE_DEFAULT, 0);

        //noinspection LoopStatementThatDoesntLoop
        for (MovieDb movie : movieList) {
            ChangesItems result = tmdb.getMovies().getMovieChanges(movie.getId(), startDate, endDate);

            assertTrue("No changes found", result.getChangedItems().size() > 0);
            break;
        }
    }
}
