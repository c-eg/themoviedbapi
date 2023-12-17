package uk.co.conoregan.themoviedbapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract test class for TMDB API tests.
 */
@Getter
public abstract class AbstractTmdbApiTest {
    private MockWebServer server;

    private String baseUrl;

    private TmdbApi tmdbApi;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Sets up the mock webserver for the test.
     * Also, creates the TMDB API instance with the mock webserver's base
     *
     * @throws IOException if the mock webserver could not be started
     */
    @BeforeEach
    public void setUp() throws IOException {
        // setup mock server
        server = new MockWebServer();
        server.start();
        baseUrl = server.url("/").toString();

        // setup tmdb api
        tmdbApi = new TmdbApi("testApiKey", baseUrl);
    }

    /**
     * Shuts down the mock webserver from the test.
     *
     * @throws IOException if the mock webserver could not be stopped
     */
    @AfterEach
    public void tearDown() throws IOException {
        server.shutdown();
    }

    /**
     * Mocks a response from the mock webserver.
     *
     * @param body         the response body
     * @param responseCode the response code
     */
    protected void mockResponse(String body, int responseCode) {
        MockResponse mockResponse = new MockResponse()
            .setResponseCode(responseCode)
            .setBody(body);
        getServer().enqueue(mockResponse);
    }

    /**
     * Tests the given object for null fields and unknown properties.
     */
    public void testForNullFieldsAndUnknownProperties(AbstractJsonMapping objectToCheck) {
        assertTrue(getNullFields(objectToCheck).isEmpty(), "Null fields found in object: " + objectToCheck);
        assertTrue(objectToCheck.getUnknownProperties().isEmpty(), "Unknown properties found in object: " + objectToCheck);
    }

    /**
     * Returns all the null fields in the given object.
     */
    private List<Field> getNullFields(Object objectToCheck) {
        List<Field> nullFields = new ArrayList<>();
        for (Field field : objectToCheck.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(objectToCheck) == null) {
                    nullFields.add(field);
                }
            }
            catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            }
        }
        return nullFields;
    }
}
