package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.core.watchproviders.WatchProviders;
import uk.co.conoregan.themoviedbapi.model.watchproviders.AvailableRegionResults;
import uk.co.conoregan.themoviedbapi.model.watchproviders.Provider;
import uk.co.conoregan.themoviedbapi.model.watchproviders.ProviderResults;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.testutil.ValidatorConfig;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;
import uk.co.conoregan.themoviedbapi.util.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbWatchProviders.TMDB_METHOD_WATCH_PROVIDERS;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        String body = TestUtils.readTestFile("api_responses/watch_providers/empty_watch_providers.json");
        WatchProviders watchProviders = JsonUtil.OBJECT_MAPPER.readValue(body, WatchProviders.class);

        assertNotNull(watchProviders);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.core.watchproviders.WatchProviders.rentProviders",
                "uk.co.conoregan.themoviedbapi.model.core.watchproviders.WatchProviders.buyProviders",
                "uk.co.conoregan.themoviedbapi.model.core.watchproviders.WatchProviders.flatrateProviders",
                "uk.co.conoregan.themoviedbapi.model.core.watchproviders.WatchProviders.freeProviders",
                "uk.co.conoregan.themoviedbapi.model.core.watchproviders.WatchProviders.adsProviders"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(watchProviders, validatorConfig);
    }
}
