package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.MovieDbResultsPage;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for keywords. See the
 * <a href="https://developer.themoviedb.org/reference/keyword-details">documentation</a> for more info.
 */
public class TmdbKeywords extends AbstractTmdbApi {
    public static final String TMDB_METHOD_KEYWORD = "keyword";

    /**
     * Create a new TmdbKeywords instance to call the keywords TMDb API methods.
     */
    TmdbKeywords(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get the basic information for a specific keyword id.
     */
    public Keyword getKeyword(String keywordId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_KEYWORD, keywordId);

        return mapJsonResult(apiUrl, Keyword.class);
    }

    /**
     * Get the list of movies for a particular keyword by id.
     *
     * @return List of movies with the keyword
     */
    public MovieDbResultsPage getKeywordMovies(String keywordId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_KEYWORD, keywordId, "movies");

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * Keyword Result Page.
     */
    public static class KeywordResultsPage extends ResultsPage<Keyword> {

    }
}
