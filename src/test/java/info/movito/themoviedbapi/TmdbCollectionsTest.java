package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;

import info.movito.themoviedbapi.model.collections.CollectionInfo;
import info.movito.themoviedbapi.model.collections.Images;
import info.movito.themoviedbapi.model.collections.Translation;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbCollections.TMDB_METHOD_COLLECTION;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbCollections}.
 */
public class TmdbCollectionsTest extends AbstractTmdbApiTest<TmdbCollections> {
    @Override
    public TmdbCollections createApiToTest() {
        return getTmdbApi().getCollections();
    }

    /**
     * Tests the {@link TmdbCollections#getDetails(Integer, String)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        int collectionId = 1;
        String language = "en";

        String body = TestUtils.readTestFile("api_responses/collections/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" +
            collectionId + "?" + "language=" + language;
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        CollectionInfo collectionInfo = getApiToTest().getDetails(collectionId, language);
        assertNotNull(collectionInfo);
        validateAbstractJsonMappingFields(collectionInfo);
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
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" +
            collectionId + "/images?language=" + language;
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Images images = getApiToTest().getImages(collectionId, language);
        assertNotNull(images);
        validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests the {@link TmdbCollections#getImages(Integer, String, String...)} with an expected result.
     * This test only check the collectionId & includeImageLanguage parameters
     */
    @Test
    public void testGetImagesMultipleLanguages() throws IOException, TmdbException {
        int collectionId = 1;
        String[] includeImageLanguage = new String[] {"en", "it"};

        String body = TestUtils.readTestFile("api_responses/collections/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" +
            collectionId + "/images?include_image_language=en%2Cit";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Images images = getApiToTest().getImages(collectionId, null, includeImageLanguage);
        assertNotNull(images);
        validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests the {@link TmdbCollections#getTranslations(Integer)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        int collectionId = 1;

        String body = TestUtils.readTestFile("api_responses/collections/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" + collectionId + "/translations";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        List<Translation> translations = getApiToTest().getTranslations(collectionId);
        assertFalse(translations.isEmpty());
    }
}
