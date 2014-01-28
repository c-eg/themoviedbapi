package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class TmdbKeywords extends AbstractApiElement {

    public static final String TMDB_METHOD_KEYWORD = "keyword";


    TmdbKeywords(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * Get the basic information for a specific keyword id.
     *
     * @param keywordId
     * @return
     */
    public Keyword getKeyword(String keywordId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_KEYWORD, keywordId);

        return mapJsonResult(apiUrl, Keyword.class);
    }


    /**
     * Get the list of movies for a particular keyword by id.
     *
     * @param keywordId
     * @param language
     * @param page
     * @return List of movies with the keyword
     */
    public List<Keyword> getKeywordMovies(String keywordId, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_KEYWORD, keywordId, "movies");

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, page);
        }


        return mapJsonResult(apiUrl, KeywordResults.class).getResults();
    }


    private static class KeywordResults extends ResultsPage<Keyword> {

    }
}
