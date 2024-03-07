package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.collections.Data;
import info.movito.themoviedbapi.model.collections.Images;
import info.movito.themoviedbapi.model.collections.Translation;
import info.movito.themoviedbapi.model.collections.Part;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbCollections.TMDB_METHOD_COLLECTION;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbCollections}.
 */
public class TmdbCollectionsTest extends AbstractTmdbApiTest {
    /**
     * Tests the {@link TmdbCollections#getDetails(Integer, String)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        int collectionId = 1;
        String language = "en";

        String body = TestUtils.readTestFile("api_responses/collections/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" +
            collectionId + "?" + "language=" + language);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        CollectionInfo collectionInfo = tmdbCollections.getDetails(collectionId, language);
        assertNotNull(collectionInfo);
        checkForNullAndEmptyFieldsAndNewItems(collectionInfo);

        List<Part> partList = collectionInfo.getParts();
        assertFalse(partList.isEmpty());

        Part part = partList.get(0);
        assertNotNull(part);
        checkForNullAndEmptyFieldsAndNewItems(part);
    }

    /**
     * Tests the {@link TmdbCollections#getImages(Integer, String, String...)} with an expected result.
     * This test only check the collectionId & language parameters
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        int collectionId = 1;
        String language = "en";

        String body = TestUtils.readTestFile("api_responses/collections/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" +
            collectionId + "/images?language=" + language);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        Images images = tmdbCollections.getImages(collectionId, language);
        assertNotNull(images);
        checkForNullAndEmptyFieldsAndNewItems(images);

        List<Artwork> posters = images.getPosters();
        assertFalse(posters.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(posters.get(0));

        List<Artwork> backdrops = images.getBackdrops();
        assertFalse(backdrops.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(backdrops.get(0));
    }

    /**
     * Tests the {@link TmdbCollections#getImages(Integer, String, String...)} with an expected result.
     * This test only check the collectionId & includeImageLanguage parameters
     */
    @Test
    public void testGetImagesMultipleLanguages() throws IOException, TmdbException {
        int collectionId = 1;
        String[] includeImageLanguage = new String[]{"en", "it"};

        String body = TestUtils.readTestFile("api_responses/collections/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" +
            collectionId + "/images?include_image_language=en%2Cit");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        Images images = tmdbCollections.getImages(collectionId, null, includeImageLanguage);
        assertNotNull(images);
        checkForNullAndEmptyFieldsAndNewItems(images);

        List<Artwork> posters = images.getPosters();
        assertFalse(posters.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(posters.get(0));

        List<Artwork> backdrops = images.getBackdrops();
        assertFalse(backdrops.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(backdrops.get(0));
    }

    /**
     * Tests the {@link TmdbCollections#getTranslations(Integer)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        int collectionId = 1;

        String body = TestUtils.readTestFile("api_responses/collections/translations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" + collectionId + "/translations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCollections tmdbCollections = getTmdbApi().getCollections();
        List<Translation> translations = tmdbCollections.getTranslations(collectionId);
        assertFalse(translations.isEmpty());

        Translation translation = translations.get(0);
        assertNotNull(translation);
        checkForNullAndEmptyFieldsAndNewItems(translation);

        Data data = translation.getData();
        assertNotNull(data);
        checkForNullAndEmptyFieldsAndNewItems(data);
    }
}
