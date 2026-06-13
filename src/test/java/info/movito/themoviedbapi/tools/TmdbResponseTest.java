package info.movito.themoviedbapi.tools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link TmdbResponse}.
 */
class TmdbResponseTest {

    /**
     * Test that the record exposes the status code and body it was created with.
     */
    @Test
    void testAccessors() {
        TmdbResponse response = new TmdbResponse(200, "{\"id\":1}");

        assertEquals(200, response.statusCode());
        assertEquals("{\"id\":1}", response.body());
    }

    /**
     * Test that a null body is permitted.
     */
    @Test
    void testNullBody() {
        TmdbResponse response = new TmdbResponse(204, null);

        assertNull(response.body());
    }

    /**
     * Test that 2xx status codes are reported as successful, including the boundaries.
     */
    @ParameterizedTest
    @ValueSource(ints = {200, 201, 204, 250, 299})
    void testIsSuccessfulHttpStatus_successful(int statusCode) {
        assertTrue(new TmdbResponse(statusCode, "body").isSuccessfulHttpStatus());
    }

    /**
     * Test that non-2xx status codes are reported as unsuccessful, including the boundaries.
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 100, 199, 300, 301, 400, 404, 429, 500, 503})
    void testIsSuccessfulHttpStatus_unsuccessful(int statusCode) {
        assertFalse(new TmdbResponse(statusCode, "body").isSuccessfulHttpStatus());
    }
}
