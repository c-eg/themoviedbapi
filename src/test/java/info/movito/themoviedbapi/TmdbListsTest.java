package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.lists.ListDetails;
import info.movito.themoviedbapi.model.lists.ListItemStatus;
import info.movito.themoviedbapi.model.lists.MovieListCreationStatus;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import info.movito.themoviedbapi.util.JsonUtil;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbLists.TMDB_METHOD_LIST;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbLists}.
 */
public class TmdbListsTest extends AbstractTmdbApiTest<TmdbLists> {
    @Override
    public TmdbLists createApiToTest() {
        return getTmdbApi().getLists();
    }

    /**
     * Tests {@link TmdbLists#addMovie(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testAddMovie() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/add_movie.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "/123/add_item?session_id=testSessionId";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_id", 456);
        String jsonBody = JsonUtil.toJson(requestBody);

        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().addMovie(123, "testSessionId", 456);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_UPDATED, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbLists#checkItemStatus(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testCheckItemStatus() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/check_item_status.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "/123/item_status?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ListItemStatus listItemStatus = getApiToTest().checkItemStatus(123, "en-US", null);
        assertNotNull(listItemStatus);
        TestUtils.validateAbstractJsonMappingFields(listItemStatus);
    }

    /**
     * Tests {@link TmdbLists#clear(Integer, String, Boolean)} with an expected result.
     */
    @Test
    public void testClear() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/clear.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "/123/clear?session_id=testSessionId&confirm=true";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().clear(123, "testSessionId", true);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_UPDATED, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbLists#create(String, String, String, String)} with an expected result.
     */
    @Test
    public void testCreate() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/create.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "?session_id=testSessionId";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "testName");
        requestBody.put("description", "testDescription");
        requestBody.put("language", "en-US");
        String jsonBody = JsonUtil.toJson(requestBody);

        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        MovieListCreationStatus responseStatus = getApiToTest().create("testSessionId", "testName", "testDescription", "en-US");
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
    }

    /**
     * Tests {@link TmdbLists#delete(Integer, String)} with an expected result.
     */
    @Test
    public void testDelete() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/delete.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "/123?session_id=testSessionId";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.DELETE)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().delete(123, "testSessionId");
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_UPDATED, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbLists#getDetails(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "/123?language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ListDetails listDetails = getApiToTest().getDetails(123, "en-US", null);
        assertNotNull(listDetails);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of("info.movito.themoviedbapi.model.lists.ListDetails.items.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(listDetails, validatorConfig);
    }

    /**
     * Tests {@link TmdbLists#removeMovie(Integer, String, Integer)} with an expected result.
     */
    @Test
    public void testRemoveMovie() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/lists/remove_movie.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_LIST + "/123/remove_item?session_id=testSessionId";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("media_id", 456);
        String jsonBody = JsonUtil.toJson(requestBody);

        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        ResponseStatus responseStatus = getApiToTest().removeMovie(123, "testSessionId", 456);
        assertNotNull(responseStatus);
        TestUtils.validateAbstractJsonMappingFields(responseStatus);
        assertEquals(TmdbResponseCode.ITEM_DELETED, responseStatus.getStatusCode());
    }
}
