package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.model.networks.AlternativeNamesResults;
import info.movito.themoviedbapi.model.networks.Network;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbNetworks.TMDB_METHOD_NETWORK;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbNetworks}.
 */
public class TmdbNetworksTest extends AbstractTmdbApiTest<TmdbNetworks> {
    @Override
    public TmdbNetworks createApiToTest() {
        return getTmdbApi().getNetworks();
    }

    /**
     * Tests the {@link TmdbNetworks#getDetails(int)} with an expected result.
     */
    @Test
    public void testGetMovieChangesList() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Network network = getApiToTest().getDetails(1);
        assertNotNull(network);
        validateAbstractJsonMappingFields(network);
    }

    /**
     * Tests the {@link TmdbNetworks#getAlternativeNames(int)} with an expected result.
     */
    @Test
    public void testGetAlternativeNames() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/alternative_names.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1/alternative_names";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AlternativeNamesResults alternativeNamesResults = getApiToTest().getAlternativeNames(1);
        assertNotNull(alternativeNamesResults);
        validateAbstractJsonMappingFields(alternativeNamesResults);
    }

    /**
     * Tests the {@link TmdbNetworks#getImages(int)} with an expected result.
     */
    @Test
    public void testGetImages() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1/images";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ImageResults imageResults = getApiToTest().getImages(1);
        assertNotNull(imageResults);
        validateAbstractJsonMappingFields(imageResults);
    }
}
