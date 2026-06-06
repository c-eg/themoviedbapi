package info.movito.themoviedbapi.tools;

import java.io.IOException;

import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.testutil.IntegrationTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.absent;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.notFound;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.status;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
@WireMockTest
class TmdbHttpClientIT {

    private static final String API_KEY = "test-api-key";

    private final TmdbHttpClient tmdbHttpClient = new TmdbHttpClient(API_KEY);

    @Test
    void testExecute_getRequest(WireMockRuntimeInfo wireMock) throws TmdbException {
        String body = "{\"id\":123}";
        stubFor(get("/movie/123")
            .willReturn(okJson(body)));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/movie/123", RequestType.GET);
        TmdbResponse result = tmdbHttpClient.execute(request);

        assertEquals(200, result.statusCode());
        assertEquals(body, result.body());
        verify(getRequestedFor(urlEqualTo("/movie/123"))
            .withHeader("Authorization", equalTo("Bearer " + API_KEY))
            .withHeader("Accept", equalTo("application/json")));
    }

    @Test
    void testExecute_postRequestWithBody(WireMockRuntimeInfo wireMock) throws TmdbException {
        String jsonBody = "{\"value\":true}";
        stubFor(post(urlEqualTo("/list"))
            .willReturn(okJson("{}")));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/list", RequestType.POST, jsonBody);
        tmdbHttpClient.execute(request);

        verify(postRequestedFor(urlEqualTo("/list"))
            .withHeader("Content-Type", equalTo("application/json"))
            .withRequestBody(equalToJson(jsonBody)));
    }

    @Test
    void testExecute_postRequestWithNullBody(WireMockRuntimeInfo wireMock) throws TmdbException {
        stubFor(post("/list")
            .willReturn(okJson("{}")));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/list", RequestType.POST);
        tmdbHttpClient.execute(request);

        verify(postRequestedFor(urlEqualTo("/list"))
            .withHeader("Content-Type", absent())
            .withRequestBody(absent()));
    }

    @Test
    void testExecute_deleteRequest(WireMockRuntimeInfo wireMock) throws TmdbException {
        stubFor(delete("/list/1")
            .willReturn(okJson("{}")));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/list/1", RequestType.DELETE);
        tmdbHttpClient.execute(request);

        verify(deleteRequestedFor(urlEqualTo("/list/1")));
    }

    @Test
    void testExecute_errorStatusIsReturned(WireMockRuntimeInfo wireMock) throws TmdbException {
        String body = "{\"status_code\":34,\"status_message\":\"The resource you requested could not be found.\"}";
        stubFor(get("/movie/0")
            .willReturn(notFound()
                .withBody(body)));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/movie/0", RequestType.GET);
        TmdbResponse result = tmdbHttpClient.execute(request);

        assertEquals(404, result.statusCode());
        assertEquals(body, result.body());
    }

    @Test
    void testExecute_retriesOnRateLimitThenSucceeds(WireMockRuntimeInfo wireMock) throws TmdbException {
        String body = "{\"id\":123}";
        stubFor(get("/movie/123")
            .inScenario("rate limit success")
            .whenScenarioStateIs(Scenario.STARTED)
            .willReturn(status(429)
                .withHeader("Retry-After", "0"))
            .willSetStateTo("retrying"));
        stubFor(get("/movie/123")
            .inScenario("rate limit success")
            .whenScenarioStateIs("retrying")
            .willReturn(okJson(body)));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/movie/123", RequestType.GET);
        TmdbResponse result = tmdbHttpClient.execute(request);

        assertEquals(200, result.statusCode());
        assertEquals(body, result.body());
        // initial attempt + 1 successful retry
        verify(2, getRequestedFor(urlEqualTo("/movie/123")));
    }

    @Test
    void testExecute_returnsLastResponseAfterExhaustingRetries(WireMockRuntimeInfo wireMock) throws TmdbException {
        stubFor(get("/movie/123")
            .willReturn(status(TmdbResponseCode.REQUEST_LIMIT_EXCEEDED.getHttpStatus())
                .withHeader("Retry-After", "0")
                .withBody("{\"status_code\":%s}".formatted(TmdbResponseCode.REQUEST_LIMIT_EXCEEDED.getTmdbCode()))));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/movie/123", RequestType.GET);
        TmdbResponse result = tmdbHttpClient.execute(request);

        assertEquals(429, result.statusCode());
        // initial attempt + 3 rate limited retries
        verify(4, getRequestedFor(urlEqualTo("/movie/123")));
    }

    @Test
    void testExecute_connectionFailureIsWrapped(WireMockRuntimeInfo wireMock) {
        stubFor(get("/movie/123")
            .willReturn(aResponse()
                .withFault(Fault.CONNECTION_RESET_BY_PEER)));

        TmdbRequest request = new TmdbRequest(wireMock.getHttpBaseUrl() + "/movie/123", RequestType.GET);

        TmdbResponseException exception = assertThrows(TmdbResponseException.class, () -> tmdbHttpClient.execute(request));
        assertInstanceOf(IOException.class, exception.getCause());
    }
}
