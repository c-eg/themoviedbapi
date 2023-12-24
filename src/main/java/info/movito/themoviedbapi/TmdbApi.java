package info.movito.themoviedbapi;

import lombok.Getter;
import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;

import java.util.List;

/**
 * The movie db api for getting started. See the
 * <a href="https://developer.themoviedb.org/docs/getting-started">documentation</a> for more info.
 *
 * @author Holger Brandl.
 */
@Getter
public class TmdbApi {
    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3/";

    private final String apiKey;

    private final String tmdbBaseUrl;

    /**
     * Constructor.
     *
     * @param apiKey your TMDB api key
     */
    public TmdbApi(String apiKey) {
        this(apiKey, TMDB_API_BASE_URL);
    }

    /**
     * Constructor.
     *
     * @param apiKey your TMDB api key
     * @param tmdbBaseUrl the base url of the TMDB api
     */
    public TmdbApi(String apiKey, String tmdbBaseUrl) {
        this.apiKey = apiKey;
        this.tmdbBaseUrl = tmdbBaseUrl;
    }

    public TmdbAccount getAccount() {
        return new TmdbAccount(this);
    }

    public TmdbAuthentication getAuthentication() {
        return new TmdbAuthentication(this);
    }

    public TmdbCertifications getCertifications() {
        return new TmdbCertifications(this);
    }

    public TmdbChanges getChanges() {
        return new TmdbChanges(this);
    }

    public TmdbCollections getCollections() {
        return new TmdbCollections(this);
    }

    public TmdbConfiguration getConfiguration() {
        return new TmdbConfiguration(this);
    }

    public TmdbCompany getCompany() {
        return new TmdbCompany(this);
    }

    public TmdbDiscover getDiscover() {
        return new TmdbDiscover(this);
    }

    public TmdbFind getFind() {
        return new TmdbFind(this);
    }

    public TmdbGenre getGenre() {
        return new TmdbGenre(this);
    }

    public TmdbKeywords getKeywords() {
        return new TmdbKeywords(this);
    }

    public TmdbLists getLists() {
        return new TmdbLists(this);
    }

    public TmdbMovies getMovies() {
        return new TmdbMovies(this);
    }

    public TmdbPeople getPeople() {
        return new TmdbPeople(this);
    }

    public TmdbReviews getReviews() {
        return new TmdbReviews(this);
    }

    public TmdbSearch getSearch() {
        return new TmdbSearch(this);
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

    /**
     * @deprecated todo: implement in config api.
     */
    @Deprecated
    public List<Timezone> getTimezones() throws TmdbResponseException {
        return new TmdbTimezones(this).getTimezones();
    }
}
