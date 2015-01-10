package info.movito.themoviedbapi.tools;

import org.apache.http.client.methods.HttpGet;
import org.yamj.api.common.http.CommonHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class HttpClientProxy implements UrlReader {

    private CommonHttpClient httpClient;


    public HttpClientProxy(CommonHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public String request(URL url, String jsonBody, RequestMethod requestMethod) {
        // todo implement json body support
        // todo implement request method support

        if (httpClient != null) {
            try {
                HttpGet httpGet = new HttpGet(url.toURI());
                httpGet.addHeader("accept", "application/json");
                return httpClient.requestContent(httpGet);
            } catch (URISyntaxException ex) {
                throw new MovieDbException(url.toString(), ex);
            } catch (IOException ex) {
                throw new MovieDbException(url.toString(), ex);
            } catch (RuntimeException ex) {
                throw new MovieDbException(url.toString(), ex);
            }
        }

        return null;
    }
}
