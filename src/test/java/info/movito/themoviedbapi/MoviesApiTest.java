package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeTitle;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.Data;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ReleaseInfo;
import info.movito.themoviedbapi.model.Translation;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.changes.ChangesItems;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.providers.ProviderResults;
import info.movito.themoviedbapi.model.providers.WatchProviders;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.UrlReader;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MoviesApiTest extends AbstractTmdbApiTest {

    @Test
    public void testGetMovieInfo() {
        MovieDb result = tmdb.getMovies().getMovie(ID_MOVIE_BLADE_RUNNER, LANGUAGE_ENGLISH);
        assertEquals("Incorrect movie information", "Blade Runner", result.getOriginalTitle());
    }

    @Test
    public void testGetMovieInfoWithAppendedMethods() {
        MovieDb result =
            tmdb.getMovies().getMovie(ID_MOVIE_BLADE_RUNNER, LANGUAGE_ENGLISH, TmdbMovies.MovieMethod.values());
        ProductionCompany productionCompany = result.getProductionCompanies().get(0);

        assertEquals("Incorrect movie information", "Blade Runner", result.getOriginalTitle());
        assertTrue("no videos", result.getVideos().size() > 0);
        assertNotNull(result.getReleases());
        assertFalse(result.getProductionCompanies().isEmpty());
        assertNotNull(productionCompany.getLogoPath());
        assertNotNull(productionCompany.getOriginCountry());
        assertNotNull(productionCompany.getName());
    }

    @Test
    public void testGetMovieAlternativeTitles() {
        String country = "";

        List<AlternativeTitle> result = tmdb.getMovies().getAlternativeTitles(ID_MOVIE_BLADE_RUNNER, country);
        assertTrue("No alternative titles found", result.size() > 0);

        country = "US";
        result = tmdb.getMovies().getAlternativeTitles(ID_MOVIE_BLADE_RUNNER, country);
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
        List<Keyword> result = tmdb.getMovies().getKeywords(ID_MOVIE_BLADE_RUNNER);
        assertFalse("No keywords found", result.isEmpty());
    }

    @Test
    public void testMovieKeywordsWithHits() {
        List<Keyword> result = tmdb.getMovies().getKeywords(550);
        assertFalse("No keywords found", result.isEmpty());
    }

    @Test
    public void testMovieKeywordsAppendedMethod() {
        //        List<Keyword> result = tmdb.getMovies().getKeywords(10191);

        MovieDb movie = tmdb.getMovies().getMovie(10191, "fr", TmdbMovies.MovieMethod.keywords);
        List<Keyword> result = movie.getKeywords();
        assertFalse("No keywords found", result.isEmpty());
        assertEquals(19, result.size());
    }

    @Test
    public void testGetMovieReleaseInfo() {
        List<ReleaseInfo> result = tmdb.getMovies().getReleaseInfo(ID_MOVIE_BLADE_RUNNER, "");
        assertFalse("Release information missing", result.isEmpty());
    }

    //    @Test
    //    public void testGetMovieTrailers() {
    //        List<Trailer> result = tmdb.getMovies().getTrailers(ID_MOVIE_BLADE_RUNNER, "");
    //        assertFalse("Movie trailers missing", result.isEmpty());
    //    }

    @Test
    public void testGetMovieVideos() {
        List<Video> result = tmdb.getMovies().getVideos(ID_MOVIE_BLADE_RUNNER, "");
        System.err.println(result);
        assertFalse("Movie trailers missing", result.isEmpty());
    }

    @Test
    public void testGetMovieTranslations() {
        List<Translation> result = tmdb.getMovies().getTranslations(ID_MOVIE_BLADE_RUNNER);
        Data translationData = result.get(0).getData();

        assertFalse("No translations found", result.isEmpty());
        assertNotNull(translationData);
        assertNotNull(translationData.getTitle());
        assertNotNull(translationData.getOverview());
        assertNotNull(translationData.getHomepage());
    }

    @Test
    public void testCreateImageUrl() {
        MovieDb movie = tmdb.getMovies().getMovie(ID_MOVIE_BLADE_RUNNER, "");
        String result = Utils.createImageUrl(tmdb, movie.getPosterPath(), "original").toString();
        assertFalse("Error compiling image URL", result.isEmpty());
    }

    @Test
    public void testGetNowPlayingMovies() {
        List<MovieDb> result = tmdb.getMovies().getNowPlayingMovies(LANGUAGE_DEFAULT, 0, null).getResults();
        assertFalse("No now playing movies found", result.isEmpty());
    }

    @Test
    public void testGetPopularMovieList() {
        List<MovieDb> result = tmdb.getMovies().getPopularMovies(LANGUAGE_DEFAULT, 0).getResults();
        assertFalse("No popular movies found", result.isEmpty());
        assertNotNull(result.get(0).getOriginalTitle());
        //        assertNotNull(result.get(0).getImdbID());

    }

    @Test
    public void testGetTopRatedMovies() {
        List<MovieDb> results = tmdb.getMovies().getTopRatedMovies(LANGUAGE_DEFAULT, 0).getResults();
        assertFalse("No top rated movies found", results.isEmpty());
    }

    @Test
    public void testGetSimilarMovies() {
        List<MovieDb> result =
            tmdb.getMovies().getSimilarMovies(ID_MOVIE_BLADE_RUNNER, LANGUAGE_DEFAULT, 0).getResults();
        assertFalse("No similar movies found", result.isEmpty());
    }

    @Test
    public void testGetLatestMovie() {
        MovieDb result = tmdb.getMovies().getLatestMovie();
        assertNotNull("No latest movie found", result);
        assertTrue("No latest movie found", result.getId() > 0);
    }

    @Test
    public void testGetUpcoming() throws Exception {

        List<MovieDb> result = tmdb.getMovies().getUpcoming("en-US", 1, null).getResults();
        assertFalse("No upcoming movies found", result.isEmpty());
    }

    @Ignore("Do not test this until it is fixed")
    public void testGetMovieChanges() throws Exception {
        String startDate = "";
        String endDate = null;

        // Get some popular movies
        List<MovieDb> movieList = tmdb.getMovies().getPopularMovies(LANGUAGE_DEFAULT, 0).getResults();

        //noinspection LoopStatementThatDoesntLoop
        for (MovieDb movie : movieList) {
            ChangesItems result = tmdb.getMovies().getChanges(movie.getId(), startDate, endDate);

            assertTrue("No changes found", result.getChangedItems().size() > 0);
            break;
        }
    }

    @Test
    public void testGetWatchProviders() throws IOException {
        UrlReader mockUrlReader = mock(UrlReader.class);

        String configResponse = TestUtils.getFixture("fixtures/config_response.json");
        String mockResponse = TestUtils.getFixture("fixtures/watch_providers.json");

        ApiUrl configApiUrl = new ApiUrl("configuration");
        configApiUrl.addParam("api_key", getApiKey());

        doReturn(configResponse)
            .when(mockUrlReader)
            .request(
                argThat(url -> url.getPath().equals(new ApiUrl("configuration").buildUrl().getPath())),
                eq(null),
                eq(RequestType.GET));

        TmdbApi testTmdb = new TmdbApi("dummy_api", mockUrlReader, true);

        int movieId = 1234;
        ApiUrl expected = new ApiUrl(TMDB_METHOD_MOVIE, movieId, TmdbMovies.MovieMethod.watch_providers);
        doReturn(mockResponse)
            .when(mockUrlReader)
            .request(
                argThat(url -> url.getPath().equals(expected.buildUrl().getPath())),
                eq(null),
                eq(RequestType.GET));

        ProviderResults results = testTmdb.getMovies()
            .getWatchProviders(movieId);

        assertTrue(true);
        assertEquals(41, results.getResults().size());

        WatchProviders gbProvider = results.getProvidersForCountry("GB");
        assertNotNull(gbProvider);
        assertEquals(8, gbProvider.getRentProviders().size());
        assertEquals(6, gbProvider.getBuyProviders().size());
        assertEquals(1, gbProvider.getFlatrateProviders().size());
        assertEquals("Virgin TV Go", gbProvider.getFlatrateProviders().get(0).getProviderName());
    }

    @Test
    public void testInvalidID() {
        try {
            tmdb.getMovies().getMovie(199392, "fr", TmdbMovies.MovieMethod.values());
            Assert.fail("exception should have been thrown");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCredits() {

        TmdbMovies tmdbMovies = tmdb.getMovies();
        // default response will not return movie credits
        MovieDb movieDb = tmdbMovies.getMovie(293660, "en");
        assertNull("No credits(cast/crew) returned", movieDb.getCredits());
        // call API requesting for credits
        // Request URL be like https://api.themoviedb.org/3/movie/293660?append_to_response=credits&language=en
        movieDb = tmdbMovies.getMovie(293660, "en", TmdbMovies.MovieMethod.credits);
        assertNotNull("Credits returned", movieDb.getCredits());
        assertTrue("Credits-cast found", movieDb.getCast().size() > 0);
        assertTrue("Credits-crew found", movieDb.getCrew().size() > 0);
    }
}
