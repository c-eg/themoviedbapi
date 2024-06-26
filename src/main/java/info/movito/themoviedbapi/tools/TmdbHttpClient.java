package info.movito.themoviedbapi.tools;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to make requests to the movie database api.
 */
@AllArgsConstructor
public class TmdbHttpClient implements TmdbUrlReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(TmdbHttpClient.class);

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private final String apiKey;

    @Override
    public String readUrl(String url, String jsonBody, RequestType requestType) throws TmdbResponseException {
        LOGGER.debug("TMDB API: making request, of type: {}, to: {}", requestType.toString(), url);

        URI uri = URI.create(url);
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
            .uri(uri)
            .header("Authorization", "Bearer " + apiKey)
            .header("Accept", "application/json")
            .header("Content-type", "application/json");

        switch (requestType) {
            case GET -> httpRequestBuilder.GET();
            case POST -> httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(jsonBody));
            case DELETE -> httpRequestBuilder.DELETE();
            default -> throw new RuntimeException("Invalid request type: " + requestType);
        }

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            if (responseBody == null) {
                throw new TmdbResponseException("Response body was null: " + httpResponse);
            }
            return responseBody;
        }
        catch (IOException | InterruptedException exception) {
            throw new TmdbResponseException(exception);
        }
    }
}
