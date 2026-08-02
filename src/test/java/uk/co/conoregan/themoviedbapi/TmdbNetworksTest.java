package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.core.image.ImageResults;
import uk.co.conoregan.themoviedbapi.model.networks.AlternativeNamesResults;
import uk.co.conoregan.themoviedbapi.model.networks.Network;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbNetworks.TMDB_METHOD_NETWORK;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Network network = getApiToTest().getDetails(1);
        assertNotNull(network);
        TestUtils.validateAbstractJsonMappingFields(network);
    }

    /**
     * Tests the {@link TmdbNetworks#getAlternativeNames(int)} with an expected result.
     */
    @Test
    public void testGetAlternativeNames() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/alternative_names.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1/alternative_names";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        AlternativeNamesResults alternativeNamesResults = getApiToTest().getAlternativeNames(1);
        assertNotNull(alternativeNamesResults);
        TestUtils.validateAbstractJsonMappingFields(alternativeNamesResults);
    }

    /**
     * Tests the {@link TmdbNetworks#getImages(int)} with an expected result.
     */
    @Test
    public void testGetImages() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1/images";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ImageResults imageResults = getApiToTest().getImages(1);
        assertNotNull(imageResults);
        TestUtils.validateAbstractJsonMappingFields(imageResults);
    }
}
