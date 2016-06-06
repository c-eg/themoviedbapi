package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.JobDepartment;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.tools.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static info.movito.themoviedbapi.TmdbMovies.MovieMethod.*;


/**
 * The MovieDb API <p> This is for version 3 of the API as specified here: http://help.themoviedb.org/kb/api/about-3
 *
 * @author Holger Brandl
 */
public class TmdbApi {

    private String apiKey;

    private TmdbConfiguration tmdbConfig;

    /**
     * Reader implementation that is used to fetch all websites
     */
    private UrlReader urlReader;

    /**
     * Automatically retry after indicated amount of seconds if we hit the request limit.
     * See http://docs.themoviedb.apiary.io/introduction/request-rate-limiting for details
     */
    private boolean autoRetry = true;


    /**
     * The language used for all language supporting requests to tmdb.
     */
//    private String language;
    public TmdbApi(String apiKey) {
        this(apiKey, new WebBrowser(), true);
    }


    public TmdbApi(String apiKey, UrlReader urlReader, boolean autoRetry) {
        this.urlReader = urlReader;
        this.apiKey = apiKey;
        this.autoRetry = autoRetry;

        try {
            tmdbConfig = new TmdbConfig(this).getConfig().getTmdbConfiguration();
        } catch (MovieDbException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new MovieDbException("Failed to read configuration", ex);
        }
    }


    /**
     * Uses the instance's api key to request information from api.tmdb.org.
     * <p/>
     * Depending on the <code>autoRetry</code> setting this method will stall and internally recurse until the request was  successfully
     * processed.
     *
     * @param apiUrl        The url to be requested
     * @param jsonBody      can be null
     * @param requestMethod
     * @return
     */
    public String requestWebPage(ApiUrl apiUrl, String jsonBody, RequestMethod requestMethod) {

        assert StringUtils.isNotBlank(apiKey);
        apiUrl.addParam(AbstractTmdbApi.PARAM_API_KEY, getApiKey());

        // inject language
//        if (isNotBlank(language)) {
//            apiUrl.addParam(AbstractApiElement.PARAM_LANGUAGE, language);
//        }


        return requestWebPageInternal(apiUrl, jsonBody, requestMethod);
    }


    private String requestWebPageInternal(ApiUrl apiUrl, String jsonBody, RequestMethod requestMethod) {
        try {
            return urlReader.request(apiUrl.buildUrl(), jsonBody, requestMethod);

        } catch (RequestCountLimitException rcle) {
            if (autoRetry) {
                Utils.sleep(rcle.getRetryAfter() * 1000);
                return requestWebPageInternal(apiUrl, jsonBody, requestMethod);
            } else {
                // just return the orignal json response if autoRetry is disabled. This will cause a ResponseStatusException.
                return rcle.getMessage();
            }
        }
    }


//    public void setLanguage(String language) {
//        this.language = language;
//    }
//
//
//    public String getLanguage() {
//        return language;
//    }


    /**
     * Get the API key that is to be used by this instance
     */
    public String getApiKey() {
        return apiKey;
    }


    public TmdbConfiguration getConfiguration() {
        return tmdbConfig;
    }


    public List<Timezone> getTimezones() {
        return new TmdbTimezones(this).getTimezones();
    }

    //
    // accessors for the different parts of the api
    //


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


    public List<JobDepartment> getJobs() {
        return new TmdbJobs(this).getJobs();
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


    /**
     * Usage example
     */
    public static void main(String[] args) {
        String apiKey = System.getenv("apikey");
        TmdbApi tmdbApi = new TmdbApi(apiKey);

        TmdbMovies movies = tmdbApi.getMovies();
//        List<MovieDb> en = movies.getTopRatedMovies("en", 0).getResults();
//        List<MovieDb> en2 = movies.getNowPlayingMovies("en", 0).getResults();
        MovieDb movie = movies.getMovie(293660, "en", credits, videos, releases, images, similar, reviews);
        List<Artwork> images = movie.getImages();
        System.out.println(movie);
    }

}
