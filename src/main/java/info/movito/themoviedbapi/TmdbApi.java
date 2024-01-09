package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.MovieDbException;
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
     * can make certain things static if necessary
     */
    @Getter(AccessLevel.PROTECTED)
    private final TmdbUrlReader tmdbUrlReader;

    private final TmdbConfiguration tmdbConfig;

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

    public TmdbConfiguration getConfiguration() {
        return tmdbConfig;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public List<Timezone> getTimezones() throws TmdbResponseException {
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
