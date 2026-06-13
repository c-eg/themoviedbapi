package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbApiClient;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for people lists. See the
 * <a href="https://developer.themoviedb.org/reference/person-popular-list">documentation</a> for more info.
 */
public class TmdbPeopleLists {
    protected static final String TMDB_METHOD_PEOPLE_LISTS = "person/popular";

    private final TmdbApiClient tmdbApiClient;

    TmdbPeopleLists(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Get a list of people ordered by popularity.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-popular-list">documentation</a> for more info.</p>
     *
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return The popular people.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PopularPersonResultsPage getPopular(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PEOPLE_LISTS)
            .addLanguage(language)
            .addPage(page);
        return tmdbApiClient.get(apiUrl, PopularPersonResultsPage.class);
    }
}
