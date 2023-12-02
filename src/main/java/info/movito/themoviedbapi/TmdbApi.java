package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.RequestCountLimitException;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.UrlReader;
import info.movito.themoviedbapi.tools.WebBrowser;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * The movie db api for getting started. See the
 * <a href="https://developer.themoviedb.org/docs/getting-started">documentation</a> for more info.
 *
 * @author Holger Brandl.
 */
public class TmdbApi {
    private final String apiKey;

    private final TmdbConfiguration tmdbConfig;

    /**
     * Reader implementation that is used to fetch all websites.
     */
    private final UrlReader urlReader;

    /**
     * Automatically retry after indicated amount of seconds if we hit the request limit. See the
     * <a href="https://developer.themoviedb.org/docs/rate-limiting">documentation</a> for details.
     */
    private boolean autoRetry = true;

    /**
     * Constructor.
     *
     * @param apiKey your TMDB api key
     */
    public TmdbApi(String apiKey) {
        this(apiKey, new WebBrowser(), true);
    }

    /**
     * Constructor.
     *
     * @param apiKey    your TMDB api key
     * @param urlReader the reader implementation that is used to fetch all websites
     * @param autoRetry automatically retry after indicated amount of seconds if we hit the request limit.
     */
    public TmdbApi(String apiKey, UrlReader urlReader, boolean autoRetry) {
        this.urlReader = urlReader;
        this.apiKey = apiKey;
        this.autoRetry = autoRetry;

        try {
            tmdbConfig = new TmdbConfig(this).getConfig().getTmdbConfiguration();
        }
        catch (MovieDbException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new MovieDbException("Failed to read configuration", ex);
        }
    }

    /**
     * Uses the instance's api key to request information from api.tmdb.org.
     *
     * Depending on the <code>autoRetry</code> setting this method will stall and internally recurse until the request was  successfully
     * processed.
     *
     * @param apiUrl        The url to be requested
     * @param jsonBody      can be null
     */
    public String requestWebPage(ApiUrl apiUrl, String jsonBody, RequestType requestType) {

        assert StringUtils.isNotBlank(apiKey);
        apiUrl.addParam(AbstractTmdbApi.PARAM_API_KEY, getApiKey());

        return requestWebPageInternal(apiUrl, jsonBody, requestType);
    }

    private String requestWebPageInternal(ApiUrl apiUrl, String jsonBody, RequestType requestType) {
        try {
            return urlReader.request(apiUrl.buildUrl(), jsonBody, requestType);
        }
        catch (RequestCountLimitException rcle) {
            if (autoRetry) {
                Utils.sleep(rcle.getRetryAfter() * 1000);
                return requestWebPageInternal(apiUrl, jsonBody, requestType);
            }
            else {
                // just return the orignal json response if autoRetry is disabled. This will cause a ResponseStatusException.
                return rcle.getMessage();
            }
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public TmdbConfiguration getConfiguration() {
        return tmdbConfig;
    }

    public List<Timezone> getTimezones() {
        return new TmdbTimezones(this).getTimezones();
    }

    public TmdbAccount getAccount() {
        return new TmdbAccount(this);
    }

    public TmdbLists getLists() {
        return new TmdbLists(this);
    }

    public TmdbMovies getMovies() {
        return new TmdbMovies(this);
    }

    public TmdbSearch getSearch() {
        return new TmdbSearch(this);
    }

    public TmdbGenre getGenre() {
        return new TmdbGenre(this);
    }

    public TmdbCompany getCompany() {
        return new TmdbCompany(this);
    }

    public TmdbCollections getCollections() {
        return new TmdbCollections(this);
    }

    public TmdbPeople getPeople() {
        return new TmdbPeople(this);
    }

    public TmdbAuthentication getAuthentication() {
        return new TmdbAuthentication(this);
    }

    public TmdbChanges getChanges() {
        return new TmdbChanges(this);
    }

    public TmdbDiscover getDiscover() {
        return new TmdbDiscover(this);
    }

    public TmdbKeywords getKeywords() {
        return new TmdbKeywords(this);
    }

    public TmdbReviews getReviews() {
        return new TmdbReviews(this);
    }

    public TmdbTV getTvSeries() {
        return new TmdbTV(this);
    }

    public TmdbTvSeasons getTvSeasons() {
        return new TmdbTvSeasons(this);
    }

    public TmdbTvEpisodes getTvEpisodes() {
        return new TmdbTvEpisodes(this);
    }

    public TmdbFind getFind() {
        return new TmdbFind(this);
    }
}
