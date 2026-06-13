package info.movito.themoviedbapi.tools;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Default {@link TmdbRequestExecutor} backed by the JDK {@link HttpClient}. Rate-limited (HTTP 429) requests are retried,
 * honoring the {@code Retry-After} response header when present.
 */
@AllArgsConstructor
@Slf4j
public class TmdbHttpClient implements TmdbRequestExecutor {

    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);

    private static final int RATE_LIMIT_STATUS_CODE = TmdbResponseCode.REQUEST_LIMIT_EXCEEDED.getHttpStatus();
    private static final int MAX_RATE_LIMIT_RETRIES = 3;
    private static final Duration DEFAULT_RATE_LIMIT_RETRY_DELAY = Duration.ofSeconds(1);

    private final String apiKey;

    private final HttpClient httpClient;

    /**
     * Creates a new client with a default {@link HttpClient}.
     *
     * @param apiKey the tmdb api key to authenticate requests with
     */
    public TmdbHttpClient(String apiKey) {
        this(apiKey, HttpClient.newBuilder()
            .connectTimeout(CONNECT_TIMEOUT)
            .build());
    }

    @Override
    public TmdbResponse execute(TmdbRequest request) throws TmdbException {
        log.debug("TMDB API: making request, of type: {}, to: {}", request.requestType(), request.url());

        HttpRequest httpRequest = buildHttpRequest(request);

        for (int attempt = 0; ; attempt++) {
            HttpResponse<String> response = sendRequest(httpRequest);

            if (RATE_LIMIT_STATUS_CODE != response.statusCode() || attempt >= MAX_RATE_LIMIT_RETRIES) {
                return new TmdbResponse(response.statusCode(), response.body());
            }

            Duration delay = retryAfterSeconds(response).orElse(DEFAULT_RATE_LIMIT_RETRY_DELAY);
            log.info("TMDB API: rate limited (HTTP {}). Waiting {} before retry {} of {}.",
                RATE_LIMIT_STATUS_CODE, delay, attempt + 1, MAX_RATE_LIMIT_RETRIES);
            sleep(delay);
        }
    }

    private HttpRequest buildHttpRequest(TmdbRequest request) {
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
            .uri(URI.create(request.url()))
            .timeout(REQUEST_TIMEOUT)
            .header("Authorization", "Bearer " + apiKey)
            .header("Accept", "application/json");

        String jsonBody = request.jsonBody();
        switch (request.requestType()) {
            case GET -> httpRequestBuilder.GET();
            case POST -> {
                if (Objects.isNull(jsonBody)) {
                    httpRequestBuilder.POST(HttpRequest.BodyPublishers.noBody());
                }
                else {
                    httpRequestBuilder.header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody));
                }
            }
            case DELETE -> httpRequestBuilder.DELETE();
            default -> throw new IllegalStateException("Unsupported request type: " + request.requestType());
        }

        return httpRequestBuilder.build();
    }

    private HttpResponse<String> sendRequest(HttpRequest httpRequest) throws TmdbException {
        try {
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new TmdbResponseException(exception);
        }
        catch (IOException exception) {
            throw new TmdbResponseException(exception);
        }
    }

    /**
     * Returns the delay requested by the {@code Retry-After} response header, if present and expressed as a number of seconds.
     */
    private static Optional<Duration> retryAfterSeconds(HttpResponse<String> response) {
        return response.headers().firstValue("Retry-After")
            .map(String::trim)
            .filter(value -> !value.isEmpty() && value.chars().allMatch(Character::isDigit))
            .map(value -> Duration.ofSeconds(Long.parseLong(value)));
    }

    private static void sleep(Duration delay) throws TmdbException {
        try {
            Thread.sleep(delay.toMillis());
        }
        catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new TmdbResponseException(exception);
        }
    }
}
