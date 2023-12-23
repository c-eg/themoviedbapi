package info.movito.themoviedbapi.tools;

import info.movito.themoviedbapi.AbstractTmdbApi;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tmdb Api URL Builder.
 * TODO: make this an actual builder. the functions should return the builder itself.
 *
 * @author Holger Brandl
 */
public class ApiEndpoint {
    private static final String APPEND_TO_RESPONSE = "append_to_response";

    private final String apiUrl;

    private final Map<String, String> params = new HashMap<>();

    /**
     * Constructor.
     */
    public ApiEndpoint(Object... urlElements) {
        StringBuilder baseUrlBuilder = new StringBuilder();

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

        apiUrl = baseUrlBuilder.toString();
    }

    /**
     * Builds the URL.
     *
     * @return the URL.
     */
    public String buildUrl() {
        StringBuilder urlBuilder = new StringBuilder(apiUrl);

        try {
            if (!params.isEmpty()) {
                List<String> keys = new ArrayList<>(params.keySet());

                for (int i = 0; i < keys.size(); i++) {
                    urlBuilder.append(i == 0 ? "?" : "&");
                    String paramName = keys.get(i);

                    urlBuilder.append(paramName).append("=");
                    urlBuilder.append(URLEncoder.encode(params.get(paramName), StandardCharsets.UTF_8));
                }
            }

            return urlBuilder.toString();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a parameter to the api endpoint (e.g. "movies/list").
     *
     * @param name the key
     * @param value the value
     */
    public void addPathParam(String name, Object value) {
        addPathParam(name, value.toString());
    }

    /**
     * Adds a parameter to the api endpoint (e.g. "movies/list").
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
     * Adds an optional parameter to the api endpoint (e.g. "movies/list").
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
     * Adds an optional, comma separated, parameter to the api endpoint (e.g. "en,null").
     *
     * @param key the key.
     * @param values the values to comma separate.
     */
    public void addQueryParamCommandSeparated(String key, String... values) {
        if (values != null && values.length > 0) {
            addPathParam(key, String.join(",", values));
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
     * Adds the page parameter to the api endpoint (e.g. "movies/list").
     *
     * @param page the page number
     */
    public void addPage(Integer page) {
        if (page != null && page > 0) {
            addPathParam(AbstractTmdbApi.PARAM_PAGE, page);
        }
    }

    /**
     * Adds the language to the api endpoint (e.g. "movies/list").
     *
     * @param language the language.
     */
    public void addLanguage(String language) {
        addQueryParam(AbstractTmdbApi.PARAM_LANGUAGE, language);
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
