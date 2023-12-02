package info.movito.themoviedbapi.tools;

import java.net.URL;

/**
 * Interface for reading URLs.
 */
public interface UrlReader {
    /**
     * Send a request to the specified URL.
     */
    String request(URL url, String jsonBody, RequestType requestType);
}
