package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.collections.CollectionInfo;
import uk.co.conoregan.themoviedbapi.model.collections.Images;
import uk.co.conoregan.themoviedbapi.model.collections.Translation;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbCollections.TMDB_METHOD_COLLECTION;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        CollectionInfo collectionInfo = getApiToTest().getDetails(collectionId, language);
        assertNotNull(collectionInfo);
        TestUtils.validateAbstractJsonMappingFields(collectionInfo);
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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Images images = getApiToTest().getImages(collectionId, language);
        assertNotNull(images);
        TestUtils.validateAbstractJsonMappingFields(images);
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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Images images = getApiToTest().getImages(collectionId, null, includeImageLanguage);
        assertNotNull(images);
        TestUtils.validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests the {@link TmdbCollections#getTranslations(Integer)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        int collectionId = 1;

        String body = TestUtils.readTestFile("api_responses/collections/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COLLECTION + "/" + collectionId + "/translations";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        List<Translation> translations = getApiToTest().getTranslations(collectionId);
        assertFalse(translations.isEmpty());
    }
}
