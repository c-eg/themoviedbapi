package uk.co.conoregan.themoviedbapi;

import uk.co.conoregan.themoviedbapi.model.core.MovieDbResultsPage;
import uk.co.conoregan.themoviedbapi.model.keywords.Keyword;
import uk.co.conoregan.themoviedbapi.tools.ApiUrl;
import uk.co.conoregan.themoviedbapi.tools.TmdbApiClient;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;

/**
 * The movie database api for keywords. See the
 * <a href="https://developer.themoviedb.org/reference/keyword-details">documentation</a> for more info.
 */
public class TmdbKeywords {
    protected static final String TMDB_METHOD_KEYWORD = "keyword";

    private final TmdbApiClient tmdbApiClient;

    /**
     * Create a new TmdbKeywords instance to call the keywords TMDb API methods.
     */
    TmdbKeywords(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    /**
     * <p>Get the details for a specific keyword.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/keyword-details">documentation</a> for more info.</p>
     *
     * @param keywordId The keyword id.
     * @return The keyword details.
     */
    public Keyword getDetails(int keywordId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_KEYWORD, keywordId);
        return tmdbApiClient.get(apiUrl, Keyword.class);
    }

    /**
     * Get the list of movies for a particular keyword by id.
     *
     * @return List of movies with the keyword
     * @deprecated use {@link TmdbDiscover#getMovie(DiscoverMovieParamBuilder)} instead.
     */
    @Deprecated
    public MovieDbResultsPage getKeywordMovies(String keywordId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_KEYWORD, keywordId, "movies")
            .addLanguage(language)
            .addPage(page);

        return tmdbApiClient.get(apiUrl, MovieDbResultsPage.class);
    }
}
