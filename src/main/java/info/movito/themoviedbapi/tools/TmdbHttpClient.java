package info.movito.themoviedbapi.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.tools.TmdbResponseCode.REQUEST_LIMIT_EXCEEDED;

/**
 * Class to make requests to the movie database api.
 */
@AllArgsConstructor
public class TmdbHttpClient implements TmdbUrlReader {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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

        Request request = requestBuilder.build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new TmdbResponseException("Response body was null: " + response);
            }
            String responseString = responseBody.string();

            try {
                // check if the response was successful. tmdb have their own codes for successful and unsuccessful responses.
                // some 2xx codes are not successful. See: https://developer.themoviedb.org/docs/errors for more info.
                ResponseStatus responseStatus = objectMapper.readValue(responseString, ResponseStatus.class);
                Integer statusCode = responseStatus.getStatusCode();
                if (statusCode != null) {
                    TmdbResponseCode tmdbResponseCode = TmdbResponseCode.fromCode(statusCode);

                    if (tmdbResponseCode != null) {
                        if (REQUEST_LIMIT_EXCEEDED == tmdbResponseCode) {
                            Thread.sleep(1000);
                            return readUrl(url, jsonBody, requestType);
                        }
                        else if (!tmdbResponseCode.isSuccess()) {
                            throw new TmdbResponseException(tmdbResponseCode);
                        }
                    }
                }
            }
            catch (JsonProcessingException exception) {
                // ignore
                // TODO: maybe improve this? it's not very efficient to parse the json to check for an error
            }

            return responseString;
        }
        catch (IOException | InterruptedException exception) {
            throw new TmdbResponseException("An unexpected error occurred: " + exception);
        }
    }
}
