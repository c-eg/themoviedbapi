package info.movito.themoviedbapi.tools;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbHttpClient}.
 */
@ExtendWith(MockitoExtension.class)
class TmdbHttpClientTest {

    private static final String API_KEY = "test-api-key";
    private static final String URL = "https://api.themoviedb.org/3/movie/123";

    private TmdbHttpClient tmdbHttpClient;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    @Captor
    private ArgumentCaptor<HttpRequest> requestCaptor;

    @BeforeEach
    void setUp() {
        tmdbHttpClient = new TmdbHttpClient(API_KEY, httpClient);
    }

    /**
     * Test that a GET request returns the response body and is built with the correct uri, method and headers.
     */
    @Test
    void testExecute_getRequest() throws IOException, InterruptedException, TmdbException {
        String body = "{\"id\":123}";
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(body);
        doReturn(httpResponse).when(httpClient).send(any(), any());

        TmdbResponse result = tmdbHttpClient.execute(new TmdbRequest(URL, RequestType.GET));

        assertEquals(200, result.statusCode());
        assertEquals(body, result.body());

        verify(httpClient).send(requestCaptor.capture(), any());
        HttpRequest request = requestCaptor.getValue();

        assertEquals(URI.create(URL), request.uri());
        assertEquals("GET", request.method());
        assertCommonHeaders(request);
        assertTrue(request.headers().firstValue("Content-Type").isEmpty());
        assertTrue(request.timeout().isPresent());
    }

    /**
     * Test that a POST request with a json body is built with the POST method and a body publisher containing the body.
     */
    @Test
    void testExecute_postRequestWithBody() throws IOException, InterruptedException, TmdbException {
        String jsonBody = "{\"value\":true}";
        doReturn(httpResponse).when(httpClient).send(any(), any());

        tmdbHttpClient.execute(new TmdbRequest(URL, RequestType.POST, jsonBody));

        verify(httpClient).send(requestCaptor.capture(), any());
        HttpRequest request = requestCaptor.getValue();

        assertCommonHeaders(request);
        assertEquals("POST", request.method());
        assertEquals("application/json", request.headers().firstValue("Content-Type").orElseThrow());
        assertTrue(request.bodyPublisher().isPresent());
        assertEquals(jsonBody.getBytes(StandardCharsets.UTF_8).length, request.bodyPublisher().orElseThrow().contentLength());
    }

    /**
     * Test that a POST request with a null body is built with the POST method and an empty body publisher.
     */
    @Test
    void testExecute_postRequestWithNullBody() throws IOException, InterruptedException, TmdbException {
        doReturn(httpResponse).when(httpClient).send(any(), any());

        tmdbHttpClient.execute(new TmdbRequest(URL, RequestType.POST));

        verify(httpClient).send(requestCaptor.capture(), any());
        HttpRequest request = requestCaptor.getValue();

        assertCommonHeaders(request);
        assertEquals("POST", request.method());
        assertTrue(request.headers().firstValue("Content-Type").isEmpty());
        assertTrue(request.bodyPublisher().isPresent());
        assertEquals(0, request.bodyPublisher().orElseThrow().contentLength());
    }

    /**
     * Test that a DELETE request is built with the DELETE method.
     */
    @Test
    void testExecute_deleteRequest() throws IOException, InterruptedException, TmdbException {
        doReturn(httpResponse).when(httpClient).send(any(), any());

        tmdbHttpClient.execute(new TmdbRequest(URL, RequestType.DELETE));

        verify(httpClient).send(requestCaptor.capture(), any());
        HttpRequest request = requestCaptor.getValue();

        assertCommonHeaders(request);
        assertEquals("DELETE", request.method());
        assertTrue(request.headers().firstValue("Content-Type").isEmpty());
    }

    /**
     * Test that an {@link IOException} thrown while sending is wrapped in a {@link TmdbResponseException}.
     */
    @Test
    void testExecute_ioExceptionIsWrapped() throws IOException, InterruptedException {
        IOException ioException = new IOException("boom");
        when(httpClient.send(any(), any())).thenThrow(ioException);

        TmdbResponseException exception = assertThrows(TmdbResponseException.class,
            () -> tmdbHttpClient.execute(new TmdbRequest(URL, RequestType.GET)));
        assertSame(ioException, exception.getCause());
    }

    /**
     * Test that an {@link InterruptedException} thrown while sending is wrapped and the thread interrupt flag is restored.
     */
    @Test
    void testExecute_interruptedExceptionIsWrappedAndInterruptFlagRestored() throws IOException, InterruptedException {
        InterruptedException interruptedException = new InterruptedException();
        when(httpClient.send(any(), any())).thenThrow(interruptedException);

        TmdbResponseException exception = assertThrows(TmdbResponseException.class,
            () -> tmdbHttpClient.execute(new TmdbRequest(URL, RequestType.GET)));
        assertSame(interruptedException, exception.getCause());
        // Thread.interrupted() returns the flag and clears it so it does not leak to other tests.
        assertTrue(Thread.interrupted());
    }

    /**
     * Asserts the authentication and accept headers that should be present on every request, regardless of type.
     */
    private void assertCommonHeaders(HttpRequest request) {
        assertEquals("Bearer " + API_KEY, request.headers().firstValue("Authorization").orElseThrow());
        assertEquals("application/json", request.headers().firstValue("Accept").orElseThrow());
    }
}
