package info.movito.themoviedbapi;

import info.movito.themoviedbapi.tools.TmdbApiClient;
import info.movito.themoviedbapi.tools.TmdbHttpClient;
import info.movito.themoviedbapi.tools.TmdbRequestExecutor;

/**
 * The movie db api for getting started. See the
 * <a href="https://developer.themoviedb.org/docs/getting-started">documentation</a> for more info.
 *
 * @author Holger Brandl, c-eg.
 */
public class TmdbApi {
    /**
     * Client used by each api class to make and map requests to the movie database api.
     */
    private final TmdbApiClient tmdbApiClient;

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
     * @param requestExecutor the request executor to use
     */
    public TmdbApi(TmdbRequestExecutor requestExecutor) {
        this.tmdbApiClient = new TmdbApiClient(requestExecutor);
    }

    public TmdbAccount getAccount() {
        return new TmdbAccount(tmdbApiClient);
    }

    public TmdbAuthentication getAuthentication() {
        return new TmdbAuthentication(tmdbApiClient);
    }

    public TmdbCertifications getCertifications() {
        return new TmdbCertifications(tmdbApiClient);
    }

    public TmdbChanges getChanges() {
        return new TmdbChanges(tmdbApiClient);
    }

    public TmdbCollections getCollections() {
        return new TmdbCollections(tmdbApiClient);
    }

    public TmdbCompanies getCompanies() {
        return new TmdbCompanies(tmdbApiClient);
    }

    public TmdbConfiguration getConfiguration() {
        return new TmdbConfiguration(tmdbApiClient);
    }

    public TmdbDiscover getDiscover() {
        return new TmdbDiscover(tmdbApiClient);
    }

    public TmdbFind getFind() {
        return new TmdbFind(tmdbApiClient);
    }

    public TmdbGenre getGenre() {
        return new TmdbGenre(tmdbApiClient);
    }

    public TmdbGuestSessions getGuestSessions() {
        return new TmdbGuestSessions(tmdbApiClient);
    }

    public TmdbKeywords getKeywords() {
        return new TmdbKeywords(tmdbApiClient);
    }

    public TmdbLists getLists() {
        return new TmdbLists(tmdbApiClient);
    }

    public TmdbMovieLists getMovieLists() {
        return new TmdbMovieLists(tmdbApiClient);
    }

    public TmdbMovies getMovies() {
        return new TmdbMovies(tmdbApiClient);
    }

    public TmdbNetworks getNetworks() {
        return new TmdbNetworks(tmdbApiClient);
    }

    public TmdbPeopleLists getPeopleLists() {
        return new TmdbPeopleLists(tmdbApiClient);
    }

    public TmdbPeople getPeople() {
        return new TmdbPeople(tmdbApiClient);
    }

    public TmdbReviews getReviews() {
        return new TmdbReviews(tmdbApiClient);
    }

    public TmdbSearch getSearch() {
        return new TmdbSearch(tmdbApiClient);
    }

    public TmdbTrending getTrending() {
        return new TmdbTrending(tmdbApiClient);
    }

    public TmdbTvEpisodes getTvEpisodes() {
        return new TmdbTvEpisodes(tmdbApiClient);
    }

    public TmdbTvEpisodeGroups getTvEpisodeGroups() {
        return new TmdbTvEpisodeGroups(tmdbApiClient);
    }

    public TmdbTvSeasons getTvSeasons() {
        return new TmdbTvSeasons(tmdbApiClient);
    }

    public TmdbTvSeries getTvSeries() {
        return new TmdbTvSeries(tmdbApiClient);
    }

    public TmdbTvSeriesLists getTvSeriesLists() {
        return new TmdbTvSeriesLists(tmdbApiClient);
    }

    public TmdbWatchProviders getWatchProviders() {
        return new TmdbWatchProviders(tmdbApiClient);
    }
}
