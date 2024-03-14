package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbHttpClient;
import info.movito.themoviedbapi.tools.TmdbUrlReader;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;

/**
 * The movie db api for getting started. See the
 * <a href="https://developer.themoviedb.org/docs/getting-started">documentation</a> for more info.
 *
 * @author Holger Brandl.
 */
public class TmdbApi {
    /**
     * Http client to make requests to the movie database api.
     */
    @Getter(AccessLevel.PROTECTED)
    private final TmdbUrlReader tmdbUrlReader;

    /**
     * Constructor.
     *
     * @param apiKey your TMDB api key
     */
    public TmdbApi(String apiKey) {
        this(new TmdbHttpClient(apiKey));
    }

    /**
     * Constructor.
     *
     * @param tmdbUrlReader the url reader to use
     */
    public TmdbApi(TmdbUrlReader tmdbUrlReader) {
        this.tmdbUrlReader = tmdbUrlReader;
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

    public TmdbCompanies getCompanies() {
        return new TmdbCompanies(this);
    }

    public TmdbConfiguration getConfiguration() {
        return new TmdbConfiguration(this);
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

    public TmdbGuestSessions getGuestSessions() {
        return new TmdbGuestSessions(this);
    }

    public TmdbKeywords getKeywords() {
        return new TmdbKeywords(this);
    }

    public TmdbLists getLists() {
        return new TmdbLists(this);
    }

    public TmdbMovieLists getMovieLists() {
        return new TmdbMovieLists(this);
    }

    public TmdbMovies getMovies() {
        return new TmdbMovies(this);
    }

    public TmdbNetworks getNetworks() {
        return new TmdbNetworks(this);
    }

    public TmdbPeopleLists getPeopleLists() {
        return new TmdbPeopleLists(this);
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

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public List<Timezone> getTimezones() throws TmdbException {
        return new TmdbTimezones(this).getTimezones();
    }

    public TmdbTvSeries getTvSeries() {
        return new TmdbTvSeries(this);
    }

    public TmdbTvEpisodes getTvEpisodes() {
        return new TmdbTvEpisodes(this);
    }

    public TmdbTvSeasons getTvSeasons() {
        return new TmdbTvSeasons(this);
    }

    public TmdbWatchProviders getWatchProviders() {
        return new TmdbWatchProviders(this);
    }
}
