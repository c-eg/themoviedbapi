package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.peoplelists.PopularPersonResults;
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
     */
    public PopularPersonResults getPopular(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PEOPLE_LISTS);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, PopularPersonResults.class);
    }
}
