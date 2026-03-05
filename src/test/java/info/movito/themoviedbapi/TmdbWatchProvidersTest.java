package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import info.movito.themoviedbapi.model.core.watchproviders.WatchProviders;
import info.movito.themoviedbapi.model.watchproviders.AvailableRegionResults;
import info.movito.themoviedbapi.model.watchproviders.Provider;
import info.movito.themoviedbapi.model.watchproviders.ProviderResults;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbWatchProviders.TMDB_METHOD_WATCH_PROVIDERS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbWatchProviders}.
 */
public class TmdbWatchProvidersTest extends AbstractTmdbApiTest<TmdbWatchProviders> {
    @Override
    public TmdbWatchProviders createApiToTest() {
        return getTmdbApi().getWatchProviders();
    }

    /**
     * Test {@link TmdbWatchProviders#getAvailableRegions(String)} with an expected result.
     */
    @Test
    public void testGetAvailableRegions() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/watch_providers/available_regions.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_WATCH_PROVIDERS + "?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AvailableRegionResults availableRegionResults = getApiToTest().getAvailableRegions("en-US");
        assertNotNull(availableRegionResults);
        TestUtils.validateAbstractJsonMappingFields(availableRegionResults);
    }

    /**
     * Test {@link TmdbWatchProviders#getMovieProviders(String, String)} with an expected result.
     */
    @Test
    public void testGetMovieProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/watch_providers/movie_providers.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_WATCH_PROVIDERS + "/movie?language=en-US&watch_region=US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ProviderResults providerResults = getApiToTest().getMovieProviders("en-US", "US");
        assertNotNull(providerResults);
        TestUtils.validateAbstractJsonMappingFields(providerResults);

        Provider provider = providerResults.getResults().get(0);
        assertNotNull(provider);

        for (Map.Entry<String, Integer> entry : provider.getDisplayPriorities().entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }

    /**
     * Test {@link TmdbWatchProviders#getTvProviders(String, String)} with an expected result.
     */
    @Test
    public void testGetTvProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/watch_providers/tv_providers.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_WATCH_PROVIDERS + "/tv?language=en-US&watch_region=US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ProviderResults providerResults = getApiToTest().getTvProviders("en-US", "US");
        assertNotNull(providerResults);
        TestUtils.validateAbstractJsonMappingFields(providerResults);

        Provider provider = providerResults.getResults().get(0);
        assertNotNull(provider);

        for (Map.Entry<String, Integer> entry : provider.getDisplayPriorities().entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }

    /**
     * Test {@link WatchProviders} possible empty fields with an expected result.
     */
    @Test
    public void testEmptyWatchProviders() throws IOException {
        String json = "{\"link\": \"https://example.com\"}";
        WatchProviders watchProviders = JsonUtil.OBJECT_MAPPER.readValue(json, WatchProviders.class);

        assertNotNull(watchProviders);
        assertNotNull(watchProviders.getRentProviders());
        assertTrue(watchProviders.getRentProviders().isEmpty());
        assertNotNull(watchProviders.getBuyProviders());
        assertTrue(watchProviders.getBuyProviders().isEmpty());
        assertNotNull(watchProviders.getFlatrateProviders());
        assertTrue(watchProviders.getFlatrateProviders().isEmpty());
        assertNotNull(watchProviders.getFreeProviders());
        assertTrue(watchProviders.getFreeProviders().isEmpty());
        assertNotNull(watchProviders.getAdsProviders());
        assertTrue(watchProviders.getAdsProviders().isEmpty());

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.core.watchproviders.WatchProviders.rentProviders",
                "info.movito.themoviedbapi.model.core.watchproviders.WatchProviders.buyProviders",
                "info.movito.themoviedbapi.model.core.watchproviders.WatchProviders.flatrateProviders",
                "info.movito.themoviedbapi.model.core.watchproviders.WatchProviders.freeProviders",
                "info.movito.themoviedbapi.model.core.watchproviders.WatchProviders.adsProviders"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(watchProviders, validatorConfig);
    }
}
