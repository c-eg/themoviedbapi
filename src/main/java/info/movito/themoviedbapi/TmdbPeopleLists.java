package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for people lists. See the
 * <a href="https://developer.themoviedb.org/reference/person-popular-list">documentation</a> for more info.
 */
public class TmdbPeopleLists extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_PEOPLE_LISTS = "person/popular";

    TmdbPeopleLists(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get a list of people ordered by popularity.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-popular-list">documentation</a> for more info.</p>
     *
     * @param language optional - The language to display the results in. e.g. "en-US".
     * @param page optional - The page of results to return.
     * @return The popular people.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PopularPersonResultsPage getPopular(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PEOPLE_LISTS);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, PopularPersonResultsPage.class);
    }
}
