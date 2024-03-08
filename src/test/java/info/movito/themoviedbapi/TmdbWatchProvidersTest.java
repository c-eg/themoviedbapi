package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.watchproviders.AvailableRegionResults;
import info.movito.themoviedbapi.model.watchproviders.Provider;
import info.movito.themoviedbapi.model.watchproviders.ProviderResults;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static info.movito.themoviedbapi.TmdbWatchProviders.TMDB_METHOD_WATCH_PROVIDERS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbWatchProviders}.
 */
public class TmdbWatchProvidersTest extends AbstractTmdbApiTest {
    /**
     * Test {@link TmdbWatchProviders#getAvailableRegions(String)} with an expected result.
     */
    @Test
    public void testGetAvailableRegions() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/watch_providers/available_regions.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_WATCH_PROVIDERS + "?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbWatchProviders tmdbWatchProviders = getTmdbApi().getWatchProviders();
        AvailableRegionResults availableRegionResults = tmdbWatchProviders.getAvailableRegions("en-US");
        assertNotNull(availableRegionResults);
        validateAbstractJsonMappingFields(availableRegionResults);
    }

    /**
     * Test {@link TmdbWatchProviders#getMovieProviders(String, String)} with an expected result.
     */
    @Test
    public void testGetMovieProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/watch_providers/movie_providers.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_WATCH_PROVIDERS + "/movie?language=en-US&watch_region=US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbWatchProviders tmdbWatchProviders = getTmdbApi().getWatchProviders();
        ProviderResults providerResults = tmdbWatchProviders.getMovieProviders("en-US", "US");
        assertNotNull(providerResults);
        validateAbstractJsonMappingFields(providerResults);

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
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_WATCH_PROVIDERS + "/tv?language=en-US&watch_region=US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbWatchProviders tmdbWatchProviders = getTmdbApi().getWatchProviders();
        ProviderResults providerResults = tmdbWatchProviders.getTvProviders("en-US", "US");
        assertNotNull(providerResults);
        validateAbstractJsonMappingFields(providerResults);

        Provider provider = providerResults.getResults().get(0);
        assertNotNull(provider);

        for (Map.Entry<String, Integer> entry : provider.getDisplayPriorities().entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }
}
