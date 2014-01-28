package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.JobDepartment;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.tools.*;
import info.movito.themoviedbapi.tools.MovieDbException.MovieDbExceptionType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


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
     * The language used for all language supporting requests to tmdb.
     */
//    private String language;
    public TmdbApi(String apiKey) {
        this(apiKey, new WebBrowser());
    }


    public TmdbApi(String apiKey, UrlReader urlReader) {
        this.urlReader = urlReader;
        this.apiKey = apiKey;

        try {
            tmdbConfig = new TmdbConfig(this).getConfig().getTmdbConfiguration();
        } catch (Throwable ex) {
            throw new MovieDbException(MovieDbExceptionType.MAPPING_FAILED, "Failed to read configuration", ex);
        }
    }


    // not json body can be null
    public String requestWebPage(ApiUrl apiUrl, String jsonBody, RequestMethod requestMethod) {

        assert StringUtils.isNotBlank(apiKey);
        apiUrl.addParam(AbstractApiElement.PARAM_API_KEY, getApiKey());

        // inject language
//        if (isNotBlank(language)) {
//            apiUrl.addParam(AbstractApiElement.PARAM_LANGUAGE, language);
//        }

        return urlReader.request(apiUrl.buildUrl(), jsonBody, requestMethod);
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


    public static void main(String[] args) {
        String apiKey = System.getenv("apikey");
        TmdbApi tmdbApi = new TmdbApi(apiKey);

        TmdbMovies movies = tmdbApi.getMovies();
        MovieDb movie = movies.getMovie(5353, "en");

        TmdbConfiguration configuration = tmdbApi.getConfiguration();

    }

}
