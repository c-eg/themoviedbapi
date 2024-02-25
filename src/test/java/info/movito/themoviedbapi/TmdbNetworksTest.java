package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeName;
import info.movito.themoviedbapi.model.core.image.Image;
import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.model.networks.AlternativeNamesResults;
import info.movito.themoviedbapi.model.networks.Network;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbNetworks.TMDB_METHOD_NETWORK;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbNetworks}.
 */
public class TmdbNetworksTest extends AbstractTmdbApiTest {
    /**
     * Tests the {@link TmdbNetworks#getDetails(int)} with an expected result.
     */
    @Test
    public void testGetMovieChangesList() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbNetworks tmdbNetworks = getTmdbApi().getNetworks();
        Network network = tmdbNetworks.getDetails(1);
        assertNotNull(network);
        testForNullFieldsAndNewItems(network);
    }

    /**
     * Tests the {@link TmdbNetworks#getAlternativeNames(int)} with an expected result.
     */
    @Test
    public void testGetAlternativeNames() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/alternative_names.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1/alternative_names");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbNetworks tmdbNetworks = getTmdbApi().getNetworks();
        AlternativeNamesResults alternativeNamesResults = tmdbNetworks.getAlternativeNames(1);
        assertNotNull(alternativeNamesResults);
        testForNullFieldsAndNewItems(alternativeNamesResults);

        List<AlternativeName> alternativeNames = alternativeNamesResults.getResults();
        assertNotNull(alternativeNames);
        assertFalse(alternativeNames.isEmpty());

        AlternativeName alternativeName = alternativeNames.get(0);
        assertNotNull(alternativeName);
        testForNullFieldsAndNewItems(alternativeName);
    }

    /**
     * Tests the {@link TmdbNetworks#getImages(int)} with an expected result.
     */
    @Test
    public void testGetImages() throws TmdbException, IOException {
        String body = TestUtils.readTestFile("api_responses/networks/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_NETWORK + "/1/images");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbNetworks tmdbNetworks = getTmdbApi().getNetworks();
        ImageResults imageResults = tmdbNetworks.getImages(1);
        assertNotNull(imageResults);
        testForNullFieldsAndNewItems(imageResults);

        List<Image> images = imageResults.getLogos();
        assertNotNull(images);
        assertFalse(images.isEmpty());

        Image image = images.get(0);
        assertNotNull(image);
        testForNullFieldsAndNewItems(image);
    }
}