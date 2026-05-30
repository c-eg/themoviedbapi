package info.movito.themoviedbapi.tools;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;

import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class to make requests to the movie database api.
 */
@AllArgsConstructor
@Slf4j
public class TmdbHttpClient implements TmdbRequestExecutor {

    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);

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

        try {
            HttpResponse<String> response = httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
            return new TmdbResponse(response.statusCode(), response.body());
        }
        catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new TmdbResponseException(exception);
        }
        catch (IOException exception) {
            throw new TmdbResponseException(exception);
        }
    }
}
