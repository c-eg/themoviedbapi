package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.tv.season.AccountStateResults;
import info.movito.themoviedbapi.model.tv.season.ChangeResults;
import info.movito.themoviedbapi.model.tv.season.ExternalIds;
import info.movito.themoviedbapi.model.tv.season.Images;
import info.movito.themoviedbapi.model.tv.core.Translations;
import info.movito.themoviedbapi.model.tv.season.TvSeasonDb;
import info.movito.themoviedbapi.model.tv.core.credits.AggregateCredits;
import info.movito.themoviedbapi.model.tv.core.credits.Credits;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.TvSeasonsAppendToResponse;
import info.movito.themoviedbapi.util.AbstractJsonMappingValidator;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static info.movito.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;
import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbTvSeasons}.
 */
public class TmdbTvSeasonsTest extends AbstractTmdbApiTest {
    /**
     * Tests the method {@link TmdbTvSeasons#getDetails(int, int, String, TvSeasonsAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        TvSeasonDb tvSeason = tmdbTvSeasons.getDetails(123, 1, "en-US");
        assertNotNull(tvSeason);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(tvSeason);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.accountStates");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.aggregateCredits");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.changes");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.credits");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.externalIds");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.images");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.translations");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.videos");
        filteredModel.add("info.movito.themoviedbapi.model.tv.season.TvSeasonDb.watchProviders");

        abstractJsonMappingValidator.validateNullFields(filteredModel);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getDetails(int, int, String, TvSeasonsAppendToResponse...)} with an expected result,
     * with append to response.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/details_with_append_to_response.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1?language=en-US&" +
            "append_to_response=account_states%2Caggregate_credits%2Ccredits%2Cexternal_ids%2Cimages%2Ctranslations%2C" +
            "videos%2Cwatch%2Fproviders");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        TvSeasonDb tvSeason = tmdbTvSeasons.getDetails(123, 1, "en-US", TvSeasonsAppendToResponse.values());
        assertNotNull(tvSeason);
        validateAbstractJsonMappingFields(tvSeason);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getAccountStates(int, int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/account_states.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/account_states?session_id=123");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        AccountStateResults accountStates = tmdbTvSeasons.getAccountStates(123, 1, "123", null);
        assertNotNull(accountStates);
        validateAbstractJsonMappingFields(accountStates);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getAggregateCredits(int, int, String)} with an expected result.
     */
    @Test
    public void testGetAggregateCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/aggregate_credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/aggregate_credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        AggregateCredits aggregateCredits = tmdbTvSeasons.getAggregateCredits(123, 1, "en-US");
        assertNotNull(aggregateCredits);
        validateAbstractJsonMappingFields(aggregateCredits);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getChanges(int, String, String, int)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/changes.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/" + TMDB_METHOD_TV_SEASON + "/123/changes?page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        ChangeResults changes = tmdbTvSeasons.getChanges(123, null, null, 1);
        assertNotNull(changes);
        validateAbstractJsonMappingFields(changes);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getCredits(int, int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        Credits credits = tmdbTvSeasons.getCredits(123, 1, "en-US");
        assertNotNull(credits);
        validateAbstractJsonMappingFields(credits);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getExternalIds(int, int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/external_ids.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/external_ids");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        ExternalIds externalIds = tmdbTvSeasons.getExternalIds(123, 1);
        assertNotNull(externalIds);
        validateAbstractJsonMappingFields(externalIds);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getImages(int, int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/images?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        Images images = tmdbTvSeasons.getImages(123, 1, "en-US");
        assertNotNull(images);
        validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getTranslations(int, int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/translations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/translations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        Translations translations = tmdbTvSeasons.getTranslations(123, 1);
        assertNotNull(translations);
        validateAbstractJsonMappingFields(translations);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getVideos(int, int, String, String...)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/videos.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/videos?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        VideoResults videos = tmdbTvSeasons.getVideos(123, 1, "en-US");
        assertNotNull(videos);
        validateAbstractJsonMappingFields(videos);
    }

    /**
     * Tests the method {@link TmdbTvSeasons#getWatchProviders(int, int, String)} with an expected result.
     */
    @Test
    public void testGetWatchProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_seasons/watch_providers.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV + "/123/" + TMDB_METHOD_TV_SEASON + "/1/watch/providers?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbTvSeasons tmdbTvSeasons = getTmdbApi().getTvSeasons();
        ProviderResults watchProviders = tmdbTvSeasons.getWatchProviders(123, 1, "en-US");
        assertNotNull(watchProviders);
        validateAbstractJsonMappingFields(watchProviders);
    }
}
