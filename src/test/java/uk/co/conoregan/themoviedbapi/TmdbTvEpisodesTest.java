package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.core.AccountStates;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.model.core.video.VideoResults;
import uk.co.conoregan.themoviedbapi.model.tv.core.ChangeResults;
import uk.co.conoregan.themoviedbapi.model.tv.core.Translations;
import uk.co.conoregan.themoviedbapi.model.tv.episode.EpisodeCredits;
import uk.co.conoregan.themoviedbapi.model.tv.episode.ExternalIds;
import uk.co.conoregan.themoviedbapi.model.tv.episode.Images;
import uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.testutil.ValidatorConfig;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponseCode;
import uk.co.conoregan.themoviedbapi.tools.appendtoresponse.TvEpisodesAppendToResponse;
import uk.co.conoregan.themoviedbapi.util.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbTvEpisodes.TMDB_METHOD_TV_EPISODE;
import static uk.co.conoregan.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;
import static uk.co.conoregan.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvEpisodeDb tvEpisode = getApiToTest().getDetails(123, 1, 1, "en-US");
        assertNotNull(tvEpisode);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb.accountStates",
                "uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb.credits",
                "uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb.externalIds",
                "uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb.images",
                "uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb.translations",
                "uk.co.conoregan.themoviedbapi.model.tv.episode.TvEpisodeDb.videos"
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

        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.POST, jsonBody))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.DELETE))).thenReturn(new TmdbResponse(200, body));

        ResponseStatus responseStatus = getApiToTest().deleteRating(123, 1, 1, null, null);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_DELETED, responseStatus.getStatusCode());
    }
}
