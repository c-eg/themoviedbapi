package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.Translation;
import info.movito.themoviedbapi.model.collections.Part;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbCollections}.
 */
public class TmdbCollectionsTest extends AbstractTmdbApiTest {
    /**
     * Tests the {@link TmdbCollections#getDetails(Integer, String)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/collections/details.json");
        mockResponse(body, 200);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        CollectionInfo collectionInfo = tmdbCollections.getDetails(1, "en");
        assertNotNull(collectionInfo);
        testForNullFieldsAndUnknownProperties(collectionInfo);

        List<Part> partList = collectionInfo.getParts();
        assertFalse(partList.isEmpty());

        Part part = partList.get(0);
        assertNotNull(part);
        testForNullFieldsAndUnknownProperties(part);
    }

    /**
     * Tests the {@link TmdbCollections#getImages(Integer, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/collections/images.json");
        mockResponse(body, 200);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        List<Artwork> artworks = tmdbCollections.getImages(1, "en", "en", null);
        assertFalse(artworks.isEmpty());

        Artwork artwork = artworks.get(0);
        assertNotNull(artwork);
        testForNullFieldsAndUnknownProperties(artwork);
    }

    /**
     * Tests the {@link TmdbCollections#getTranslations(Integer)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/collections/translations.json");
        mockResponse(body, 200);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        List<Translation> translations = tmdbCollections.getTranslations(1);
        assertFalse(translations.isEmpty());

        Translation translation = translations.get(0);
        assertNotNull(translation);
        testForNullFieldsAndUnknownProperties(translation);
    }
}
