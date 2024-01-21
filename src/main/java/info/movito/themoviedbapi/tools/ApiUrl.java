package info.movito.themoviedbapi.tools;

import info.movito.themoviedbapi.AbstractTmdbApi;
import info.movito.themoviedbapi.tools.sortby.SortBy;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Tmdb Api URL Builder.
 *
 * @author Holger Brandl
 */
public class ApiUrl {
    public static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3/";

    private static final String APPEND_TO_RESPONSE = "append_to_response";

    private final String baseUrl;

    private final Map<String, String> params = new LinkedHashMap<>();

    /**
     * Constructor.
     */
    public ApiUrl(Object... urlElements) {
        StringBuilder baseUrlBuilder = new StringBuilder(TMDB_API_BASE_URL);

        for (int i = 0; i < urlElements.length; i++) {
            Object object = urlElements[i];

            if (object == null) {
                throw new IllegalArgumentException("url element can not be null");
            }

            baseUrlBuilder.append(object);

            if (i < urlElements.length - 1) {
                baseUrlBuilder.append("/");
            }
        }

        baseUrl = baseUrlBuilder.toString();
    }

    /**
     * Builds the URL.
     *
     * @return the URL.
     */
    public URL buildUrl() {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(urlBuilder.toString().contains("?") ? "&" : "?")
                          .append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            }

            return new URL(urlBuilder.toString());
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a parameter to the API url.
     *
     * @param name the key
     * @param value the value
     */
    public void addPathParam(String name, Object value) {
        addPathParam(name, value.toString());
    }

    /**
     * Adds a parameter to the API url.
     *
     * @param name the key
     * @param value the value
     */
    public void addPathParam(String name, String value) {
        if (params.containsKey(name)) {
            throw new RuntimeException("paramater '" + name + "' already defined");
        }

        name = StringUtils.trimToEmpty(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("parameter name can not be empty");
        }

        value = StringUtils.trimToEmpty(value);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("value of parameter '" + name + "' can not be empty");
        }

        params.put(name, value);
    }

    /**
     * Adds a parameter to the API url.
     *
     * @param key the key
     * @param value the value
     */
    public void addPathParam(String key, int value) {
        addPathParam(key, Integer.toString(value));
    }

    /**
     * Adds a parameter to the API url.
     *
     * @param key the key
     * @param value the value
     */
    public void addPathParam(String key, boolean value) {
        addPathParam(key, Boolean.toString(value));
    }

    /**
     * Adds an optional parameter to the api endpoint.
     *
     * @param key the key.
     * @param value the value.
     */
    public void addQueryParam(String key, Object value) {
        if (value != null) {
            addPathParam(key, value);
        }
    }

    /**
     * Adds the page parameter to the API url.
     *
     * @param page the page number
     */
    public void addPage(Integer page) {
        if (page != null && page > 0) {
            addPathParam(AbstractTmdbApi.PARAM_PAGE, page);
        }
    }

    /**
     * Adds the language to the API url.
     *
     * @param language the language.
     */
    public void addLanguage(String language) {
        if (isNotBlank(language)) {
            addPathParam(AbstractTmdbApi.PARAM_LANGUAGE, language);
        }
    }

    /**
     * Convenience wrapper around addArgument.
     *
     * @param appendToResponse Comma separated, any movie method
     */
    public void appendToResponse(String... appendToResponse) {
        if (appendToResponse == null || appendToResponse.length == 0) {
            return;
        }

        addPathParam(APPEND_TO_RESPONSE, String.join(",", appendToResponse));
    }

    /**
     * Adds the sort by ordering to the api endpoint.
     *
     * @param sortBy the order to sort.
     */
    public void addSortBy(SortBy sortBy) {
        if (sortBy != null) {
            addQueryParam(AbstractTmdbApi.PARAM_SORT_BY, sortBy.getValue());
        }
    }
}
