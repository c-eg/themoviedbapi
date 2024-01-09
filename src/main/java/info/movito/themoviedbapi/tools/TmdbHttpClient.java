package info.movito.themoviedbapi.tools;

import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;

/**
 * Class to make requests to the movie database api.
 */
@AllArgsConstructor
public class TmdbHttpClient implements TmdbUrlReader {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private final String apiKey;

    @Override
    public String readUrl(URL url, String jsonBody, RequestType requestType) throws TmdbResponseException {
        Request.Builder requestBuilder = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Accept", "application/json")
            .addHeader("Content-type", "application/json");

        switch (requestType) {
            case GET -> requestBuilder.get();
            case POST -> requestBuilder.post(okhttp3.RequestBody.create(jsonBody, okhttp3.MediaType.parse("application/json")));
            case DELETE -> requestBuilder.delete();
            default -> throw new RuntimeException("Invalid request type: " + requestType);
        }

        try (Response response = okHttpClient.newCall(requestBuilder.build()).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new TmdbResponseException("Response body was null: " + response);
            }

            return responseBody.string();
        }
        catch (IOException exception) {
            throw new TmdbResponseException(exception);
        }
    }
}
