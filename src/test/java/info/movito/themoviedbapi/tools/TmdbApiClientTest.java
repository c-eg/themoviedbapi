package info.movito.themoviedbapi.tools;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbApiClient}.
 */
@ExtendWith(MockitoExtension.class)
class TmdbApiClientTest {

    private static final ApiUrl API_URL = new ApiUrl("movie", 123);
    private static final String URL = API_URL.buildUrl();

    private TmdbApiClient tmdbApiClient;

    @Mock
    private TmdbRequestExecutor requestExecutor;

    @Captor
    private ArgumentCaptor<TmdbRequest> requestCaptor;

    @BeforeEach
    void setUp() {
        tmdbApiClient = new TmdbApiClient(requestExecutor);
    }

    /**
     * Test that a GET request maps a successful response body to the requested class.
     */
    @Test
    void testGet_class() throws TmdbException {
        String body = "{\"status_code\":1,\"status_message\":\"Success.\"}";
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ResponseStatus result = tmdbApiClient.get(API_URL, ResponseStatus.class);
        assertEquals("Success.", result.getStatusMessage());
    }

    /**
     * Test that a GET request maps a successful response body to the requested generic type.
     */
    @Test
    void testGet_typeReference() throws TmdbException {
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET))).thenReturn(new TmdbResponse(200, "[\"a\",\"b\"]"));

        List<String> result = tmdbApiClient.get(API_URL, new TypeReference<>() {
        });

        assertEquals(List.of("a", "b"), result);
    }

    /**
     * Test that the executor is called with a request carrying the url, request type and json body.
     */
    @Test
    void testRequest() throws TmdbException {
        String jsonBody = "{\"value\":true}";
        when(requestExecutor.execute(any())).thenReturn(new TmdbResponse(200, "{\"status_message\":\"ok\"}"));

        ResponseStatus result = tmdbApiClient.request(API_URL, jsonBody, RequestType.POST, ResponseStatus.class);
        assertEquals("ok", result.getStatusMessage());

        verify(requestExecutor).execute(requestCaptor.capture());
        TmdbRequest request = requestCaptor.getValue();

        assertEquals(URL, request.url());
        assertEquals(RequestType.POST, request.requestType());
        assertEquals(jsonBody, request.jsonBody());
    }

    /**
     * Test that an unsuccessful HTTP status with a recognisable TMDB status code throws with that code.
     */
    @Test
    void testRequest_unsuccessfulHttpStatusWithResponseCode_throws() throws TmdbException {
        String body = "{\"status_code\":7,\"status_message\":\"Invalid API key.\"}";
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET))).thenReturn(new TmdbResponse(401, body));

        TmdbResponseException exception = assertThrows(TmdbResponseException.class, () -> tmdbApiClient.get(API_URL, ResponseStatus.class));
        assertEquals(TmdbResponseCode.INVALID_API_KEY, exception.getResponseCode());
    }

    /**
     * Test that an unsuccessful HTTP status with no recognisable TMDB status code throws with the http status surfaced.
     */
    @Test
    void testRequest_unsuccessfulHttpStatusWithoutResponseCode_throws() throws TmdbException {
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET)))
            .thenReturn(new TmdbResponse(502, "<html>Bad Gateway</html>"));

        TmdbResponseException exception = assertThrows(TmdbResponseException.class, () -> tmdbApiClient.get(API_URL, ResponseStatus.class));
        assertNull(exception.getResponseCode());
        assertTrue(exception.getMessage().contains("502"));
    }

    /**
     * Test that a successful (2xx) response is mapped even when the body carries a non-successful TMDB status code.
     */
    @Test
    void testRequest_successfulHttpStatusWithFailureBodyCode_isMapped() throws TmdbException {
        String body = "{\"status_code\":21,\"status_message\":\"Entry not found.\"}";
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ResponseStatus result = tmdbApiClient.get(API_URL, ResponseStatus.class);
        assertEquals(TmdbResponseCode.ENTRY_NOT_FOUND, result.getStatusCode());
    }

    /**
     * Test that a body that cannot be mapped to the result type on a successful response throws a {@link TmdbException}.
     */
    @Test
    void testRequest_unmappableSuccessfulBody_throws() throws TmdbException {
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET))).thenReturn(new TmdbResponse(200, "[1, 2, 3]"));

        assertThrowsExactly(TmdbException.class, () -> tmdbApiClient.get(API_URL, ResponseStatus.class));
    }

    /**
     * Test that an exception thrown by the executor is propagated unchanged.
     */
    @Test
    void testRequest_propagatesExecutorException() throws TmdbException {
        TmdbException executorException = new TmdbException("boom");
        when(requestExecutor.execute(new TmdbRequest(URL, RequestType.GET))).thenThrow(executorException);

        TmdbException exception = assertThrows(TmdbException.class, () -> tmdbApiClient.get(API_URL, ResponseStatus.class));
        assertSame(executorException, exception);
    }
}
