package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.ReviewResultsPage;
import info.movito.themoviedbapi.model.core.TvKeywords;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.tv.core.ChangeResults;
import info.movito.themoviedbapi.model.tv.core.credits.AggregateCredits;
import info.movito.themoviedbapi.model.tv.core.credits.Credits;
import info.movito.themoviedbapi.model.tv.series.AlternativeTitleResults;
import info.movito.themoviedbapi.model.tv.series.ContentRatingResults;
import info.movito.themoviedbapi.model.tv.series.EpisodeGroupResults;
import info.movito.themoviedbapi.model.tv.series.ExternalIds;
import info.movito.themoviedbapi.model.tv.series.Images;
import info.movito.themoviedbapi.model.tv.series.ScreenedTheatricallyResults;
import info.movito.themoviedbapi.model.tv.series.Translations;
import info.movito.themoviedbapi.model.tv.series.TvSeriesDb;
import info.movito.themoviedbapi.model.tv.series.TvSeriesListResultsPage;
import info.movito.themoviedbapi.testutil.AbstractJsonMappingValidator;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import info.movito.themoviedbapi.tools.appendtoresponse.TvSeriesAppendToResponse;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.testutil.TestUtils.validateAbstractJsonMappingFields;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesDb tvSeries = getApiToTest().getDetails(123, "en-US");
        assertNotNull(tvSeries);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(tvSeries);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.accountStates");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.aggregateCredits");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.alternativeTitles");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.changes");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.contentRatings");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.credits");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.episodeGroups");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.externalIds");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.images");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.keywords");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.lists");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.recommendations");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.reviews");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.screenedTheatrically");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.similar");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.translations");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.videos");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.watchProviders");

        abstractJsonMappingValidator.validateNullFields(filteredModel);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
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
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesDb tvSeries = getApiToTest().getDetails(123, "en-US", TvSeriesAppendToResponse.values());
        assertNotNull(tvSeries);
        validateAbstractJsonMappingFields(tvSeries);
    }

    /**
     * Test for {@link TmdbTvSeries#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/account_states.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/account_states?session_id=123";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AccountStates accountStates = getApiToTest().getAccountStates(123, "123", null);
        assertNotNull(accountStates);
        validateAbstractJsonMappingFields(accountStates);
    }

    /**
     * Test for {@link TmdbTvSeries#getAggregateCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetAggregateCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/aggregate_credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/aggregate_credits?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AggregateCredits aggregateCredits = getApiToTest().getAggregateCredits(123, "en-US");
        assertNotNull(aggregateCredits);
        validateAbstractJsonMappingFields(aggregateCredits);
    }

    /**
     * Test for {@link TmdbTvSeries#getAlternativeTitles(int)} with an expected result.
     */
    @Test
    public void testGetAlternativeTitles() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/alternative_titles.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/alternative_titles";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AlternativeTitleResults alternativeTitles = getApiToTest().getAlternativeTitles(123);
        assertNotNull(alternativeTitles);
        validateAbstractJsonMappingFields(alternativeTitles);
    }

    /**
     * Test for {@link TmdbTvSeries#getChanges(int, String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/changes.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/changes?page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ChangeResults changes = getApiToTest().getChanges(123, null, null, 1);
        assertNotNull(changes);
        validateAbstractJsonMappingFields(changes);
    }

    /**
     * Test for {@link TmdbTvSeries#getContentRatings(int)} with an expected result.
     */
    @Test
    public void testGetContentRatings() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/content_ratings.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/content_ratings";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ContentRatingResults contentRatings = getApiToTest().getContentRatings(123);
        assertNotNull(contentRatings);
        validateAbstractJsonMappingFields(contentRatings);
    }

    /**
     * Test for {@link TmdbTvSeries#getCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/credits?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Credits credits = getApiToTest().getCredits(123, "en-US");
        assertNotNull(credits);
        validateAbstractJsonMappingFields(credits);
    }

    /**
     * Test for {@link TmdbTvSeries#getEpisodeGroups(int)} with an expected result.
     */
    @Test
    public void testGetEpisodeGroups() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/episode_groups.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/episode_groups";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        EpisodeGroupResults episodeGroups = getApiToTest().getEpisodeGroups(123);
        assertNotNull(episodeGroups);
        validateAbstractJsonMappingFields(episodeGroups);
    }

    /**
     * Test for {@link TmdbTvSeries#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/external_ids.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/external_ids";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ExternalIds externalIds = getApiToTest().getExternalIds(123);
        assertNotNull(externalIds);
        validateAbstractJsonMappingFields(externalIds);
    }

    /**
     * Test for {@link TmdbTvSeries#getImages(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/images";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Images images = getApiToTest().getImages(123, null);
        assertNotNull(images);
        validateAbstractJsonMappingFields(images);
    }

    /**
     * Test for {@link TmdbTvSeries#getKeywords(int)} with an expected result.
     */
    @Test
    public void testGetKeywords() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/keywords.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/keywords";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvKeywords keywords = getApiToTest().getKeywords(123);
        assertNotNull(keywords);
        validateAbstractJsonMappingFields(keywords);
    }

    /**
     * Test for {@link TmdbTvSeries#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/latest.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/latest";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesDb latest = getApiToTest().getLatest();
        assertNotNull(latest);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(latest);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.accountStates");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.aggregateCredits");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.alternativeTitles");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.changes");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.contentRatings");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.credits");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.episodeGroups");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.externalIds");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.images");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.keywords");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.lists");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.recommendations");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.reviews");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.screenedTheatrically");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.similar");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.translations");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.videos");
        filteredModel.add("info.movito.themoviedbapi.model.tv.series.TvSeriesDb.watchProviders");

        abstractJsonMappingValidator.validateNullFields(filteredModel);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Test for {@link TmdbTvSeries#getLists(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/lists.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/lists";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesListResultsPage lists = getApiToTest().getLists(123, null, null);
        assertNotNull(lists);
        validateAbstractJsonMappingFields(lists);
    }

    /**
     * Test for {@link TmdbTvSeries#getRecommendations(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetRecommendations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/recommendations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/recommendations";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage recommendations = getApiToTest().getRecommendations(123, null, null);
        assertNotNull(recommendations);
        validateAbstractJsonMappingFields(recommendations);
    }

    /**
     * Test for {@link TmdbTvSeries#getReviews(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetReviews() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/reviews.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/reviews";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ReviewResultsPage reviews = getApiToTest().getReviews(123, null, null);
        assertNotNull(reviews);
        validateAbstractJsonMappingFields(reviews);
    }

    /**
     * Test for {@link TmdbTvSeries#getScreenedTheatrically(int)} with an expected result.
     */
    @Test
    public void testGetScreenedTheatrically() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/screened_theatrically.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/screened_theatrically";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ScreenedTheatricallyResults screenedTheatrically = getApiToTest().getScreenedTheatrically(123);
        assertNotNull(screenedTheatrically);
        validateAbstractJsonMappingFields(screenedTheatrically);
    }

    /**
     * Test for {@link TmdbTvSeries#getSimilar(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetSimilar() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/similar.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/similar";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage similar = getApiToTest().getSimilar(123, null, null);
        assertNotNull(similar);
        validateAbstractJsonMappingFields(similar);
    }

    /**
     * Test for {@link TmdbTvSeries#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/translations";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Translations translations = getApiToTest().getTranslations(123);
        assertNotNull(translations);
        validateAbstractJsonMappingFields(translations);
    }

    /**
     * Test for {@link TmdbTvSeries#getVideos(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/videos.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/videos";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        VideoResults videos = getApiToTest().getVideos(123, null);
        assertNotNull(videos);
        validateAbstractJsonMappingFields(videos);
    }

    /**
     * Test for {@link TmdbTvSeries#getWatchProviders(int)} with an expected result.
     */
    @Test
    public void testGetWatchProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_series/watch_providers.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/watch/providers";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ProviderResults watchProviders = getApiToTest().getWatchProviders(123);
        assertNotNull(watchProviders);
        validateAbstractJsonMappingFields(watchProviders);
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
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().addRating(123, null, null, 2.1);
        assertNotNull(responseStatus);
        validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Test for {@link TmdbTvSeries#deleteRating(int, String, String)} with an expected result.
     */
    @Test
    public void testDeleteRating() throws IOException, TmdbException {
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/rating";
        String body = TestUtils.readTestFile("api_responses/tv_series/delete_rating.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.DELETE)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().deleteRating(123, null, null);
        assertNotNull(responseStatus);
        validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_DELETED, responseStatus.getStatusCode());
    }
}
