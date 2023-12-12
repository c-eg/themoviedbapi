package uk.co.conoregan.themoviedbapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

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
    public void mockResponse(String body, int responseCode) {
        MockResponse mockResponse = new MockResponse()
            .setResponseCode(responseCode)
            .setBody(body);
        getServer().enqueue(mockResponse);
    }
}
