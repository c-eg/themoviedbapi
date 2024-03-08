package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.movies.AccountStates;
import info.movito.themoviedbapi.model.movies.AlternativeTitles;
import info.movito.themoviedbapi.model.movies.Credits;
import info.movito.themoviedbapi.model.movies.ExternalIds;
import info.movito.themoviedbapi.model.movies.Images;
import info.movito.themoviedbapi.model.movies.KeywordResults;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.movies.Rated;
import info.movito.themoviedbapi.model.movies.ReleaseDateResults;
import info.movito.themoviedbapi.model.movies.ReviewResultsPage;
import info.movito.themoviedbapi.model.movies.Translations;
import info.movito.themoviedbapi.model.movies.VideoResults;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.providers.ProviderResults;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.MovieAppendToResponse;
import info.movito.themoviedbapi.util.AbstractJsonMappingValidator;
import info.movito.themoviedbapi.util.FieldsToIgnore;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static info.movito.themoviedbapi.AbstractTmdbApi.getObjectMapper;
import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for throws IOException, TmdbException {@link TmdbMovies}.
 */
public class TmdbMoviesTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbMovies#getDetails(int, String, MovieAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieDb movie = tmdbMovies.getDetails(123, "en-US");
        assertNotNull(movie);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(movie);
        List<FieldsToIgnore> fieldsToIgnore = new ArrayList<>();
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "accountStates", "accountStates"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "alternativeTitles", "alternativeTitles"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "credits", "credits"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "changes", "changes"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "externalIds", "externalIds"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "images", "images"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "keywords", "keywords"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "recommendations", "recommendations"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "releaseDates", "releaseDates"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "lists", "lists"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "reviews", "reviews"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "similar", "similar"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "translations", "translations"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "videos", "videos"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "watchProviders", "watchProviders"));

        abstractJsonMappingValidator.validateNullFields(fieldsToIgnore);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Tests {@link TmdbMovies#getDetails(int, String, MovieAppendToResponse...)} with appended responses, with an expected result.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/details_with_append_to_response.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123?language=en-US&append_to_response=account_states%2C" +
            "alternative_titles%2Ccredits%2Cchanges%2Cexternal_ids%2Cimages%2Ckeywords%2Clists%2Crecommendations%2Crelease_dates%2C" +
            "reviews%2Csimilar%2Ctranslations%2Cvideos%2Cwatch%2Fproviders");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieDb movie = tmdbMovies.getDetails(123, "en-US", MovieAppendToResponse.values());
        assertNotNull(movie);
        checkForNullAndEmptyFieldsAndNewItems(movie);
    }

    /**
     * Tests {@link TmdbMovies#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/account_states.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/account_states?session_id=123");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        AccountStates accountStates = tmdbMovies.getAccountStates(123, "123", null);
        assertNotNull(accountStates);
        checkForNullAndEmptyFieldsAndNewItems(accountStates);

        Optional<Rated> rated = accountStates.getRated();
        assertNotNull(rated);
        assertTrue(rated.isPresent());
        checkForNullAndEmptyFieldsAndNewItems(rated.get());
    }

    /**
     * Tests {@link TmdbMovies#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAlternativeTitles() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/alternative_titles.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/alternative_titles?country=US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        AlternativeTitles alternativeTitles = tmdbMovies.getAlternativeTitles(123, "US");
        assertNotNull(alternativeTitles);
        checkForNullAndEmptyFieldsAndNewItems(alternativeTitles);
    }

    /**
     * Tests {@link TmdbMovies#getChanges(int, String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/changes.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/changes");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ChangeResults changeResults = tmdbMovies.getChanges(123, null, null, null);
        assertNotNull(changeResults);
        checkForNullAndEmptyFieldsAndNewItems(changeResults);
    }

    /**
     * Tests {@link TmdbMovies#getCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        Credits credits = tmdbMovies.getCredits(123, "en-US");
        assertNotNull(credits);
        checkForNullAndEmptyFieldsAndNewItems(credits);
    }

    /**
     * Tests {@link TmdbMovies#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/external_ids.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/external_ids");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ExternalIds externalIds = tmdbMovies.getExternalIds(123);
        assertNotNull(externalIds);
        checkForNullAndEmptyFieldsAndNewItems(externalIds);
    }

    /**
     * Tests {@link TmdbMovies#getImages(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/images?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        Images images = tmdbMovies.getImages(123, "en-US");
        assertNotNull(images);
        checkForNullAndEmptyFieldsAndNewItems(images);
    }

    /**
     * Tests {@link TmdbMovies#getKeywords(int)} with an expected result.
     */
    @Test
    public void testGetKeywords() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/keywords.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/keywords");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        KeywordResults keywords = tmdbMovies.getKeywords(123);
        assertNotNull(keywords);
        checkForNullAndEmptyFieldsAndNewItems(keywords);
    }

    /**
     * Tests {@link TmdbMovies#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/latest.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/latest");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieDb movie = tmdbMovies.getLatest();
        assertNotNull(movie);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(movie);
        List<FieldsToIgnore> fieldsToIgnore = new ArrayList<>();
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "accountStates", "accountStates"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "alternativeTitles", "alternativeTitles"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "credits", "credits"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "changes", "changes"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "externalIds", "externalIds"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "images", "images"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "keywords", "keywords"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "recommendations", "recommendations"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "releaseDates", "releaseDates"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "lists", "lists"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "reviews", "reviews"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "similar", "similar"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "translations", "translations"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "videos", "videos"));
        fieldsToIgnore.add(new FieldsToIgnore(MovieDb.class, "watchProviders", "watchProviders"));

        abstractJsonMappingValidator.validateNullFields(fieldsToIgnore);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Tests {@link TmdbMovies#getLists(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/lists.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/lists?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieListResultsPage lists = tmdbMovies.getLists(123, "en-US", null);
        assertNotNull(lists);
        checkForNullAndEmptyFieldsAndNewItems(lists);
    }

    /**
     * Tests {@link TmdbMovies#getRecommendations(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetRecommendations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/recommendations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/recommendations?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieResultsPage recommendations = tmdbMovies.getRecommendations(123, "en-US", 1);
        assertNotNull(recommendations);
        checkForNullAndEmptyFieldsAndNewItems(recommendations);
    }

    /**
     * Tests {@link TmdbMovies#getReleaseDates(int)} with an expected result.
     */
    @Test
    public void testGetReleaseDates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/release_dates.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/release_dates");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ReleaseDateResults releaseDates = tmdbMovies.getReleaseDates(123);
        assertNotNull(releaseDates);
        checkForNullAndEmptyFieldsAndNewItems(releaseDates);
    }

    /**
     * Tests {@link TmdbMovies#getReviews(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetReviews() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/reviews.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/reviews?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ReviewResultsPage reviews = tmdbMovies.getReviews(123, "en-US", null);
        assertNotNull(reviews);
        checkForNullAndEmptyFieldsAndNewItems(reviews);
    }

    /**
     * Tests {@link TmdbMovies#getSimilar(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetSimilar() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/similar.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/similar?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieResultsPage similar = tmdbMovies.getSimilar(123, "en-US", null);
        assertNotNull(similar);
        checkForNullAndEmptyFieldsAndNewItems(similar);
    }

    /**
     * Tests {@link TmdbMovies#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/translations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/translations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        Translations translations = tmdbMovies.getTranslations(123);
        assertNotNull(translations);
        checkForNullAndEmptyFieldsAndNewItems(translations);
    }

    /**
     * Tests {@link TmdbMovies#getVideos(int, String)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/videos.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/videos?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        VideoResults videos = tmdbMovies.getVideos(123, "en-US");
        assertNotNull(videos);
        checkForNullAndEmptyFieldsAndNewItems(videos);
    }

    /**
     * Tests {@link TmdbMovies#getWatchProviders(int)} with an expected result.
     */
    @Test
    public void testGetWatchProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/watch_providers.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/watch/providers");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ProviderResults watchProviders = tmdbMovies.getWatchProviders(123);
        assertNotNull(watchProviders);
        checkForNullAndEmptyFieldsAndNewItems(watchProviders);
    }

    /**
     * Tests {@link TmdbMovies#addRating(int, String, String, double)} with an expected result.
     */
    @Test
    public void testAddRating() throws IOException, TmdbException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("value", 2.1);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/rating");
        String body = TestUtils.readTestFile("api_responses/movies/add_rating.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ResponseStatus responseStatus = tmdbMovies.addRating(123, null, null, 2.1);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbMovies#deleteRating(int, String, String)} with an expected result.
     */
    @Test
    public void testDeleteRating() throws IOException, TmdbException {
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/rating");
        String body = TestUtils.readTestFile("api_responses/movies/delete_rating.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.DELETE)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ResponseStatus responseStatus = tmdbMovies.deleteRating(123, null, null);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(13, responseStatus.getStatusCode());
    }
}
