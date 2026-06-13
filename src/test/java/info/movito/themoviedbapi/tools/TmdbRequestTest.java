package info.movito.themoviedbapi.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link TmdbRequest}.
 */
class TmdbRequestTest {

    private static final String URL = "https://api.themoviedb.org/3/movie/123";

    /**
     * Test that the full constructor exposes the url, request type and json body it was created with.
     */
    @Test
    void testAccessors() {
        TmdbRequest request = new TmdbRequest(URL, RequestType.POST, "{\"value\":true}");

        assertEquals(URL, request.url());
        assertEquals(RequestType.POST, request.requestType());
        assertEquals("{\"value\":true}", request.jsonBody());
    }

    /**
     * Test that the convenience constructor leaves the json body null.
     */
    @Test
    void testConstructorWithoutBody() {
        TmdbRequest request = new TmdbRequest(URL, RequestType.GET);

        assertEquals(URL, request.url());
        assertEquals(RequestType.GET, request.requestType());
        assertNull(request.jsonBody());
    }

    /**
     * Test that a null json body is permitted.
     */
    @Test
    void testNullBodyIsAllowed() {
        TmdbRequest request = new TmdbRequest(URL, RequestType.GET, null);
        assertNull(request.jsonBody());
    }

    /**
     * Test that a null url is rejected by the full constructor.
     */
    @Test
    void testNullUrlThrows() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new TmdbRequest(null, RequestType.GET, null));
        assertEquals("url must not be null", exception.getMessage());
    }

    /**
     * Test that a null request type is rejected.
     */
    @Test
    void testNullRequestTypeThrows() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new TmdbRequest(URL, null, null));
        assertEquals("requestType must not be null", exception.getMessage());
    }

    /**
     * Test that the convenience constructor also rejects a null url.
     */
    @Test
    void testNullUrlThrows_convenienceConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new TmdbRequest(null, RequestType.GET));
        assertEquals("url must not be null", exception.getMessage());
    }
}
