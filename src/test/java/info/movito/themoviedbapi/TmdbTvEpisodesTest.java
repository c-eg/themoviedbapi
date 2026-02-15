package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.tv.core.ChangeResults;
import info.movito.themoviedbapi.model.tv.core.Translations;
import info.movito.themoviedbapi.model.tv.episode.EpisodeCredits;
import info.movito.themoviedbapi.model.tv.episode.ExternalIds;
import info.movito.themoviedbapi.model.tv.episode.Images;
import info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import info.movito.themoviedbapi.tools.appendtoresponse.TvEpisodesAppendToResponse;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbTvEpisodes.TMDB_METHOD_TV_EPISODE;
import static info.movito.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;
import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbTvEpisodes}.
 */
public class TmdbTvEpisodesTest extends AbstractTmdbApiTest<TmdbTvEpisodes> {
    @Override
    public TmdbTvEpisodes createApiToTest() {
        return getTmdbApi().getTvEpisodes();
    }

    /**
     * Tests {@link TmdbTvEpisodes#getDetails(int, int, int, String, TvEpisodesAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvEpisodeDb tvEpisode = getApiToTest().getDetails(123, 1, 1, "en-US");
        assertNotNull(tvEpisode);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb.accountStates",
                "info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb.credits",
                "info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb.externalIds",
                "info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb.images",
                "info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb.translations",
                "info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb.videos"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(tvEpisode, validatorConfig);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getDetails(int, int, int, String, TvEpisodesAppendToResponse...)} with an expected result,
     * with append to response.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/details_with_append_to_response.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1?language=en-US&append_to_response=account_states%2Ccredits%2Cexternal_ids%2Cimages%2Ctranslations%2Cvideos";

        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvEpisodeDb tvEpisode = getApiToTest().getDetails(123, 1, 1, "en-US", TvEpisodesAppendToResponse.values());
        assertNotNull(tvEpisode);
        TestUtils.validateAbstractJsonMappingFields(tvEpisode);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getAccountStates(int, int, int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/account_states.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/account_states";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AccountStates accountStates = getApiToTest().getAccountStates(123, 1, 1, null, null);
        assertNotNull(accountStates);
        TestUtils.validateAbstractJsonMappingFields(accountStates);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getChanges(int)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/changes.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/" + TMDB_METHOD_TV_EPISODE + "/1/changes";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ChangeResults changeResults = getApiToTest().getChanges(1);
        assertNotNull(changeResults);
        TestUtils.validateAbstractJsonMappingFields(changeResults);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getCredits(int, int, int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/credits?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        EpisodeCredits credits = getApiToTest().getCredits(123, 1, 1, "en-US");
        assertNotNull(credits);
        TestUtils.validateAbstractJsonMappingFields(credits);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getExternalIds(int, int, int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/external_ids.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/external_ids";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ExternalIds externalIds = getApiToTest().getExternalIds(123, 1, 1);
        assertNotNull(externalIds);
        TestUtils.validateAbstractJsonMappingFields(externalIds);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getImages(int, int, int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/images?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Images images = getApiToTest().getImages(123, 1, 1, "en-US");
        assertNotNull(images);
        TestUtils.validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getTranslations(int, int, int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/translations";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Translations translations = getApiToTest().getTranslations(123, 1, 1);
        assertNotNull(translations);
        TestUtils.validateAbstractJsonMappingFields(translations);
    }

    /**
     * Tests {@link TmdbTvEpisodes#getVideos(int, int, int, String, String...)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episodes/videos.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/videos?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        VideoResults videoResults = getApiToTest().getVideos(123, 1, 1, "en-US");
        assertNotNull(videoResults);
        TestUtils.validateAbstractJsonMappingFields(videoResults);
    }

    /**
     * Tests {@link TmdbTvEpisodes#addRating(int, int, int, String, String, double)} with an expected result.
     */
    @Test
    public void testAddRating() throws IOException, TmdbException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("value", 2.1);
        String jsonBody = JsonUtil.toJson(requestBody);

        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/rating";
        String body = TestUtils.readTestFile("api_responses/tv_episodes/add_rating.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().addRating(123, 1, 1, null, null, 2.1);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.SUCCESS, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbTvEpisodes#deleteRating(int, int, int, String, String)} with an expected result.
     */
    @Test
    public void testDeleteRating() throws IOException, TmdbException {
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/" + TMDB_METHOD_TV_EPISODE +
            "/1/rating";
        String body = TestUtils.readTestFile("api_responses/tv_episodes/delete_rating.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.DELETE)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().deleteRating(123, 1, 1, null, null);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_DELETED, responseStatus.getStatusCode());
    }
}
