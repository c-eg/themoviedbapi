package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ReviewResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.movies.AlternativeTitles;
import info.movito.themoviedbapi.model.movies.Credits;
import info.movito.themoviedbapi.model.movies.ExternalIds;
import info.movito.themoviedbapi.model.movies.Images;
import info.movito.themoviedbapi.model.movies.KeywordResults;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.movies.ReleaseDateResults;
import info.movito.themoviedbapi.model.movies.Translations;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import info.movito.themoviedbapi.tools.appendtoresponse.MovieAppendToResponse;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbMovies}.
 */
public class TmdbMoviesTest extends AbstractTmdbApiTest<TmdbMovies> {
    @Override
    public TmdbMovies createApiToTest() {
        return getTmdbApi().getMovies();
    }

    /**
     * Tests {@link TmdbMovies#getDetails(int, String, MovieAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieDb movie = getApiToTest().getDetails(123, "en-US");
        assertNotNull(movie);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.movies.MovieDb.accountStates",
                "info.movito.themoviedbapi.model.movies.MovieDb.alternativeTitles",
                "info.movito.themoviedbapi.model.movies.MovieDb.credits",
                "info.movito.themoviedbapi.model.movies.MovieDb.changes",
                "info.movito.themoviedbapi.model.movies.MovieDb.externalIds",
                "info.movito.themoviedbapi.model.movies.MovieDb.images",
                "info.movito.themoviedbapi.model.movies.MovieDb.keywords",
                "info.movito.themoviedbapi.model.movies.MovieDb.recommendations",
                "info.movito.themoviedbapi.model.movies.MovieDb.releaseDates",
                "info.movito.themoviedbapi.model.movies.MovieDb.lists",
                "info.movito.themoviedbapi.model.movies.MovieDb.reviews",
                "info.movito.themoviedbapi.model.movies.MovieDb.similar",
                "info.movito.themoviedbapi.model.movies.MovieDb.translations",
                "info.movito.themoviedbapi.model.movies.MovieDb.videos",
                "info.movito.themoviedbapi.model.movies.MovieDb.watchProviders"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movie, validatorConfig);
    }

    /**
     * Tests {@link TmdbMovies#getDetails(int, String, MovieAppendToResponse...)} with appended responses, with an expected result.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/details_with_append_to_response.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123?language=en-US&append_to_response=account_states%2C" +
            "alternative_titles%2Ccredits%2Cchanges%2Cexternal_ids%2Cimages%2Ckeywords%2Clists%2Crecommendations%2Crelease_dates%2C" +
            "reviews%2Csimilar%2Ctranslations%2Cvideos%2Cwatch%2Fproviders";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieDb movie = getApiToTest().getDetails(123, "en-US", MovieAppendToResponse.values());
        assertNotNull(movie);

        // origin_country is only returned by the details endpoint, not in nested results or other endpoints
        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.movies.MovieDb.recommendations.results.originCountry",
                "info.movito.themoviedbapi.model.movies.MovieDb.similar.results.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movie, validatorConfig);
    }

    /**
     * Tests {@link TmdbMovies#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/account_states.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/account_states?session_id=123";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AccountStates accountStates = getApiToTest().getAccountStates(123, "123", null);
        assertNotNull(accountStates);
        TestUtils.validateAbstractJsonMappingFields(accountStates);
    }

    /**
     * Tests {@link TmdbMovies#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAlternativeTitles() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/alternative_titles.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/alternative_titles?country=US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AlternativeTitles alternativeTitles = getApiToTest().getAlternativeTitles(123, "US");
        assertNotNull(alternativeTitles);
        TestUtils.validateAbstractJsonMappingFields(alternativeTitles);
    }

    /**
     * Tests {@link TmdbMovies#getChanges(int, String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/changes.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/changes";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ChangeResults changeResults = getApiToTest().getChanges(123, null, null, null);
        assertNotNull(changeResults);
        TestUtils.validateAbstractJsonMappingFields(changeResults);
    }

    /**
     * Tests {@link TmdbMovies#getCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/credits?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Credits credits = getApiToTest().getCredits(123, "en-US");
        assertNotNull(credits);
        TestUtils.validateAbstractJsonMappingFields(credits);
    }

    /**
     * Tests {@link TmdbMovies#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/external_ids.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/external_ids";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ExternalIds externalIds = getApiToTest().getExternalIds(123);
        assertNotNull(externalIds);
        TestUtils.validateAbstractJsonMappingFields(externalIds);
    }

    /**
     * Tests {@link TmdbMovies#getImages(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/images?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Images images = getApiToTest().getImages(123, "en-US");
        assertNotNull(images);
        TestUtils.validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests {@link TmdbMovies#getKeywords(int)} with an expected result.
     */
    @Test
    public void testGetKeywords() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/keywords.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/keywords";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        KeywordResults keywords = getApiToTest().getKeywords(123);
        assertNotNull(keywords);
        TestUtils.validateAbstractJsonMappingFields(keywords);
    }

    /**
     * Tests {@link TmdbMovies#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/latest.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/latest";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieDb movie = getApiToTest().getLatest();
        assertNotNull(movie);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.movies.MovieDb.accountStates",
                "info.movito.themoviedbapi.model.movies.MovieDb.alternativeTitles",
                "info.movito.themoviedbapi.model.movies.MovieDb.credits",
                "info.movito.themoviedbapi.model.movies.MovieDb.changes",
                "info.movito.themoviedbapi.model.movies.MovieDb.externalIds",
                "info.movito.themoviedbapi.model.movies.MovieDb.images",
                "info.movito.themoviedbapi.model.movies.MovieDb.keywords",
                "info.movito.themoviedbapi.model.movies.MovieDb.recommendations",
                "info.movito.themoviedbapi.model.movies.MovieDb.releaseDates",
                "info.movito.themoviedbapi.model.movies.MovieDb.lists",
                "info.movito.themoviedbapi.model.movies.MovieDb.reviews",
                "info.movito.themoviedbapi.model.movies.MovieDb.similar",
                "info.movito.themoviedbapi.model.movies.MovieDb.translations",
                "info.movito.themoviedbapi.model.movies.MovieDb.videos",
                "info.movito.themoviedbapi.model.movies.MovieDb.watchProviders"
            ))
            .emptyCollectionFieldsToIgnore(List.of(
                // origin_country is NOT returned by the 'latest' movie endpoint
                "info.movito.themoviedbapi.model.movies.MovieDb.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movie, validatorConfig);
    }

    /**
     * Tests {@link TmdbMovies#getLists(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/lists.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/lists?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieListResultsPage lists = getApiToTest().getLists(123, "en-US", null);
        assertNotNull(lists);
        TestUtils.validateAbstractJsonMappingFields(lists);
    }

    /**
     * Tests {@link TmdbMovies#getRecommendations(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetRecommendations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/recommendations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/recommendations?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage recommendations = getApiToTest().getRecommendations(123, "en-US", 1);
        assertNotNull(recommendations);

        // origin_country is NOT returned for movie recommendations
        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.core.MovieResultsPage.results.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(recommendations, validatorConfig);
    }

    /**
     * Tests {@link TmdbMovies#getReleaseDates(int)} with an expected result.
     */
    @Test
    public void testGetReleaseDates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/release_dates.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/release_dates";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ReleaseDateResults releaseDates = getApiToTest().getReleaseDates(123);
        assertNotNull(releaseDates);
        TestUtils.validateAbstractJsonMappingFields(releaseDates);
    }

    /**
     * Tests {@link TmdbMovies#getReviews(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetReviews() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/reviews.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/reviews?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ReviewResultsPage reviews = getApiToTest().getReviews(123, "en-US", null);
        assertNotNull(reviews);
        TestUtils.validateAbstractJsonMappingFields(reviews);
    }

    /**
     * Tests {@link TmdbMovies#getSimilar(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetSimilar() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/similar.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/similar?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage similar = getApiToTest().getSimilar(123, "en-US", null);
        assertNotNull(similar);

        // origin_country is NOT returned for similar movies
        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.core.MovieResultsPage.results.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(similar, validatorConfig);
    }

    /**
     * Tests {@link TmdbMovies#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/translations";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Translations translations = getApiToTest().getTranslations(123);
        assertNotNull(translations);
        TestUtils.validateAbstractJsonMappingFields(translations);
    }

    /**
     * Tests {@link TmdbMovies#getVideos(int, String)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/videos.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/videos?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        VideoResults videos = getApiToTest().getVideos(123, "en-US");
        assertNotNull(videos);
        TestUtils.validateAbstractJsonMappingFields(videos);
    }

    /**
     * Tests {@link TmdbMovies#getWatchProviders(int)} with an expected result.
     */
    @Test
    public void testGetWatchProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/watch_providers.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/watch/providers";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ProviderResults watchProviders = getApiToTest().getWatchProviders(123);
        assertNotNull(watchProviders);
        TestUtils.validateAbstractJsonMappingFields(watchProviders);
    }

    /**
     * Tests {@link TmdbMovies#addRating(int, String, String, double)} with an expected result.
     */
    @Test
    public void testAddRating() throws IOException, TmdbException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("value", 2.1);
        String jsonBody = JsonUtil.toJson(requestBody);

        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/rating";
        String body = TestUtils.readTestFile("api_responses/movies/add_rating.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().addRating(123, null, null, 2.1);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbMovies#deleteRating(int, String, String)} with an expected result.
     */
    @Test
    public void testDeleteRating() throws IOException, TmdbException {
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/rating";
        String body = TestUtils.readTestFile("api_responses/movies/delete_rating.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.DELETE)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().deleteRating(123, null, null);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_DELETED, responseStatus.getStatusCode());
    }
}
