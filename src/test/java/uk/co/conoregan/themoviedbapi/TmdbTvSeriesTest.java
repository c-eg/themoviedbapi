package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.core.AccountStates;
import uk.co.conoregan.themoviedbapi.model.core.ReviewResultsPage;
import uk.co.conoregan.themoviedbapi.model.core.TvKeywords;
import uk.co.conoregan.themoviedbapi.model.core.TvSeriesResultsPage;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.model.core.video.VideoResults;
import uk.co.conoregan.themoviedbapi.model.core.watchproviders.ProviderResults;
import uk.co.conoregan.themoviedbapi.model.tv.core.ChangeResults;
import uk.co.conoregan.themoviedbapi.model.tv.core.credits.AggregateCredits;
import uk.co.conoregan.themoviedbapi.model.tv.core.credits.Credits;
import uk.co.conoregan.themoviedbapi.model.tv.series.AlternativeTitleResults;
import uk.co.conoregan.themoviedbapi.model.tv.series.ContentRatingResults;
import uk.co.conoregan.themoviedbapi.model.tv.series.EpisodeGroupResults;
import uk.co.conoregan.themoviedbapi.model.tv.series.ExternalIds;
import uk.co.conoregan.themoviedbapi.model.tv.series.Images;
import uk.co.conoregan.themoviedbapi.model.tv.series.ScreenedTheatricallyResults;
import uk.co.conoregan.themoviedbapi.model.tv.series.Translations;
import uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb;
import uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesListResultsPage;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.testutil.ValidatorConfig;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponseCode;
import uk.co.conoregan.themoviedbapi.tools.appendtoresponse.TvSeriesAppendToResponse;
import uk.co.conoregan.themoviedbapi.util.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbTvSeries}.
 */
public class TmdbTvSeriesTest extends AbstractTmdbApiTest<TmdbTvSeries> {
    @Override
    public TmdbTvSeries createApiToTest() {
        return getTmdbApi().getTvSeries();
    }

    /**
     * Test for {@link TmdbTvSeries#getDetails(int, String, TvSeriesAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvSeriesDb tvSeries = getApiToTest().getDetails(123, "en-US");
        assertNotNull(tvSeries);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.accountStates",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.aggregateCredits",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.alternativeTitles",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.changes",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.contentRatings",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.credits",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.episodeGroups",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.externalIds",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.images",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.keywords",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.lists",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.recommendations",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.reviews",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.screenedTheatrically",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.similar",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.translations",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.videos",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.watchProviders"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(tvSeries, validatorConfig);
    }

    /**
     * Test for {@link TmdbTvSeries#getDetails(int, String, TvSeriesAppendToResponse...)} with an expected result, with append to response.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/details_with_append_to_response.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123?language=en-US&append_to_response=account_states%2Caggregate_credits" +
            "%2Calternative_titles%2Cchanges%2Ccontent_ratings%2Ccredits%2Cepisode_groups%2Cexternal_ids%2Cimages%2Ckeywords%2Clists" +
            "%2Crecommendations%2Creviews%2Cscreened_theatrically%2Csimilar%2Ctranslations%2Cvideos%2Cwatch%2Fproviders";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvSeriesDb tvSeries = getApiToTest().getDetails(123, "en-US", TvSeriesAppendToResponse.values());
        assertNotNull(tvSeries);

        TestUtils.validateAbstractJsonMappingFields(tvSeries);
    }

    /**
     * Test for {@link TmdbTvSeries#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/account_states.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/account_states?session_id=123";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        AccountStates accountStates = getApiToTest().getAccountStates(123, "123", null);
        assertNotNull(accountStates);
        TestUtils.validateAbstractJsonMappingFields(accountStates);
    }

    /**
     * Test for {@link TmdbTvSeries#getAggregateCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetAggregateCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/aggregate_credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/aggregate_credits?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        AggregateCredits aggregateCredits = getApiToTest().getAggregateCredits(123, "en-US");
        assertNotNull(aggregateCredits);
        TestUtils.validateAbstractJsonMappingFields(aggregateCredits);
    }

    /**
     * Test for {@link TmdbTvSeries#getAlternativeTitles(int)} with an expected result.
     */
    @Test
    public void testGetAlternativeTitles() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/alternative_titles.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/alternative_titles";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        AlternativeTitleResults alternativeTitles = getApiToTest().getAlternativeTitles(123);
        assertNotNull(alternativeTitles);
        TestUtils.validateAbstractJsonMappingFields(alternativeTitles);
    }

    /**
     * Test for {@link TmdbTvSeries#getChanges(int, String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/changes.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/changes?page=1";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ChangeResults changes = getApiToTest().getChanges(123, null, null, 1);
        assertNotNull(changes);
        TestUtils.validateAbstractJsonMappingFields(changes);
    }

    /**
     * Test for {@link TmdbTvSeries#getContentRatings(int)} with an expected result.
     */
    @Test
    public void testGetContentRatings() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/content_ratings.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/content_ratings";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ContentRatingResults contentRatings = getApiToTest().getContentRatings(123);
        assertNotNull(contentRatings);
        TestUtils.validateAbstractJsonMappingFields(contentRatings);
    }

    /**
     * Test for {@link TmdbTvSeries#getCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/credits?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Credits credits = getApiToTest().getCredits(123, "en-US");
        assertNotNull(credits);
        TestUtils.validateAbstractJsonMappingFields(credits);
    }

    /**
     * Test for {@link TmdbTvSeries#getEpisodeGroups(int)} with an expected result.
     */
    @Test
    public void testGetEpisodeGroups() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/episode_groups.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/episode_groups";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        EpisodeGroupResults episodeGroups = getApiToTest().getEpisodeGroups(123);
        assertNotNull(episodeGroups);
        TestUtils.validateAbstractJsonMappingFields(episodeGroups);
    }

    /**
     * Test for {@link TmdbTvSeries#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/external_ids.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/external_ids";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ExternalIds externalIds = getApiToTest().getExternalIds(123);
        assertNotNull(externalIds);
        TestUtils.validateAbstractJsonMappingFields(externalIds);
    }

    /**
     * Test for {@link TmdbTvSeries#getImages(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/images";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Images images = getApiToTest().getImages(123, null);
        assertNotNull(images);
        TestUtils.validateAbstractJsonMappingFields(images);
    }

    /**
     * Test for {@link TmdbTvSeries#getKeywords(int)} with an expected result.
     */
    @Test
    public void testGetKeywords() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/keywords.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/keywords";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvKeywords keywords = getApiToTest().getKeywords(123);
        assertNotNull(keywords);
        TestUtils.validateAbstractJsonMappingFields(keywords);
    }

    /**
     * Test for {@link TmdbTvSeries#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/latest.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/latest";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvSeriesDb latest = getApiToTest().getLatest();
        assertNotNull(latest);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.accountStates",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.aggregateCredits",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.alternativeTitles",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.changes",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.contentRatings",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.credits",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.episodeGroups",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.externalIds",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.images",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.keywords",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.lists",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.recommendations",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.reviews",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.screenedTheatrically",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.similar",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.translations",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.videos",
                "uk.co.conoregan.themoviedbapi.model.tv.series.TvSeriesDb.watchProviders"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(latest, validatorConfig);
    }

    /**
     * Test for {@link TmdbTvSeries#getLists(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/lists.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/lists";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvSeriesListResultsPage lists = getApiToTest().getLists(123, null, null);
        assertNotNull(lists);
        TestUtils.validateAbstractJsonMappingFields(lists);
    }

    /**
     * Test for {@link TmdbTvSeries#getRecommendations(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetRecommendations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/recommendations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/recommendations";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvSeriesResultsPage recommendations = getApiToTest().getRecommendations(123, null, null);
        assertNotNull(recommendations);
        TestUtils.validateAbstractJsonMappingFields(recommendations);
    }

    /**
     * Test for {@link TmdbTvSeries#getReviews(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetReviews() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/reviews.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/reviews";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ReviewResultsPage reviews = getApiToTest().getReviews(123, null, null);
        assertNotNull(reviews);
        TestUtils.validateAbstractJsonMappingFields(reviews);
    }

    /**
     * Test for {@link TmdbTvSeries#getScreenedTheatrically(int)} with an expected result.
     */
    @Test
    public void testGetScreenedTheatrically() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/screened_theatrically.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/screened_theatrically";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ScreenedTheatricallyResults screenedTheatrically = getApiToTest().getScreenedTheatrically(123);
        assertNotNull(screenedTheatrically);
        TestUtils.validateAbstractJsonMappingFields(screenedTheatrically);
    }

    /**
     * Test for {@link TmdbTvSeries#getSimilar(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetSimilar() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/similar.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/similar";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvSeriesResultsPage similar = getApiToTest().getSimilar(123, null, null);
        assertNotNull(similar);
        TestUtils.validateAbstractJsonMappingFields(similar);
    }

    /**
     * Test for {@link TmdbTvSeries#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/translations";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Translations translations = getApiToTest().getTranslations(123);
        assertNotNull(translations);
        TestUtils.validateAbstractJsonMappingFields(translations);
    }

    /**
     * Test for {@link TmdbTvSeries#getVideos(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/videos.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/videos";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        VideoResults videos = getApiToTest().getVideos(123, null);
        assertNotNull(videos);
        TestUtils.validateAbstractJsonMappingFields(videos);
    }

    /**
     * Test for {@link TmdbTvSeries#getWatchProviders(int)} with an expected result.
     */
    @Test
    public void testGetWatchProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/watch_providers.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/watch/providers";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ProviderResults watchProviders = getApiToTest().getWatchProviders(123);
        assertNotNull(watchProviders);

        TestUtils.validateAbstractJsonMappingFields(watchProviders);
    }

    /**
     * Test for {@link TmdbTvSeries#addRating(int, String, String, double)} with an expected result.
     */
    @Test
    public void testAddRating() throws IOException, TmdbException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("value", 2.1);
        String jsonBody = JsonUtil.toJson(requestBody);

        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/rating";
        String body = TestUtils.readTestFile("api_responses/tv_series/add_rating.json");
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.POST, jsonBody))).thenReturn(new TmdbResponse(200, body));

        ResponseStatus responseStatus = getApiToTest().addRating(123, null, null, 2.1);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Test for {@link TmdbTvSeries#deleteRating(int, String, String)} with an expected result.
     */
    @Test
    public void testDeleteRating() throws IOException, TmdbException {
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/rating";
        String body = TestUtils.readTestFile("api_responses/tv_series/delete_rating.json");
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.DELETE))).thenReturn(new TmdbResponse(200, body));

        ResponseStatus responseStatus = getApiToTest().deleteRating(123, null, null);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_DELETED, responseStatus.getStatusCode());
    }
}
