package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.MovieListResultsPage;
import uk.co.conoregan.themoviedbapi.model.account.MovieResultsPage;
import uk.co.conoregan.themoviedbapi.model.account.TvSeriesResultsPage;
import uk.co.conoregan.themoviedbapi.model.config.Account;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.model.rated.RatedMovieResultsPage;
import uk.co.conoregan.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import uk.co.conoregan.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.SortBy;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.util.Utils;

import java.util.HashMap;

/**
 * The movie database api for accounts. See the
 * <a href="https://developer.themoviedb.org/reference/account-details">documentation</a> for more info.
 */
public class TmdbAccount extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_ACCOUNT = "account";

    public static final String PARAM_SESSION = "session_id";

    /**
     * Create a new TmdbAccount instance to call the account related TMDb API methods.
     */
    TmdbAccount(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get the basic information for an account. You will need to have a valid session id.
     * See the <a href="https://developer.themoviedb.org/reference/account-details">documentation</a> for more info.
     */
    public Account getDetails(String sessionToken) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT);
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Account.class);
    }

    /**
     * Add media from an account's favorites list.
     * See the <a href="https://developer.themoviedb.org/reference/account-add-favorite">documentation</a> for more info.
     */
    public ResponseStatus addFavorite(Integer accountId, String sessionToken, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeFavoriteStatus(accountId, sessionToken, mediaId, mediaType, true);
    }

    /**
     * Remove media from an account's favorites list.
     * See the <a href="https://developer.themoviedb.org/reference/account-add-favorite">documentation</a> for more info.
     */
    public ResponseStatus removeFavorite(Integer accountId, String sessionToken, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeFavoriteStatus(accountId, sessionToken, mediaId, mediaType, false);
    }

    private ResponseStatus changeFavoriteStatus(Integer accountId, String sessionToken, Integer mediaId, MediaType mediaType,
                                                boolean isFavorite) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "favorite");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        HashMap<String, Object> body = new HashMap<>();
        body.put("media_type", mediaType.toString());
        body.put("media_id", mediaId);
        body.put("favorite", isFavorite);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, ResponseStatus.class);
    }

    /**
     * Add media to an account's watch list.
     * See the <a href="https://developer.themoviedb.org/reference/account-add-to-watchlist">documentation</a> for more info.
     */
    public ResponseStatus addToWatchList(Integer accountId, String sessionToken, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeWatchListStatus(accountId, sessionToken, mediaId, mediaType, true);
    }

    /**
     * Add media to an account's watch list.
     * See the <a href="https://developer.themoviedb.org/reference/account-add-to-watchlist">documentation</a> for more info.
     */
    public ResponseStatus removeFromWatchList(Integer accountId, String sessionToken, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeWatchListStatus(accountId, sessionToken, mediaId, mediaType, false);
    }

    private ResponseStatus changeWatchListStatus(Integer accountId, String sessionToken, Integer mediaId, MediaType mediaType,
                                         boolean isWatchList) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "watchlist");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);

        HashMap<String, Object> body = new HashMap<>();
        body.put("media_type", mediaType.toString());
        body.put("media_id", mediaId);
        body.put("watchlist", isWatchList);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, ResponseStatus.class);
    }

    /**
     * Get the favourite movies from the account.
     */
    public MovieResultsPage getFavoriteMovies(Integer accountId, String sessionToken, String language, Integer page,
                                              SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "favorite/movies");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieResultsPage.class);
    }

    /**
     * Get the favorite tv shows from the account.
     */
    public TvSeriesResultsPage getFavoriteTv(Integer accountId, String sessionToken, String language, Integer page,
                                             SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "favorite/tv");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesResultsPage.class);
    }

    /**
     * Get the lists that as user has created.
     */
    public MovieListResultsPage getLists(Integer accountId, String sessionToken, Integer page)
        throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "lists");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieListResultsPage.class);
    }

    /**
     * Get the rated movies from the account.
     */
    public RatedMovieResultsPage getRatedMovies(Integer accountId, String sessionToken, String language, Integer page,
                                                SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "rated/movies");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, RatedMovieResultsPage.class);
    }

    /**
     * Get the rated tv shows from the account.
     */
    public RatedTvSeriesResultsPage getRatedTvSeries(Integer accountId, String sessionToken, String language, Integer page,
                                                     SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "rated/tv");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, RatedTvSeriesResultsPage.class);
    }

    /**
     * Get the rated tv episodes from the account.
     */
    public RatedTvEpisodeResultsPage getRatedTvEpisodes(Integer accountId, String sessionToken, String language, Integer page,
                                                        SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "rated/tv/episodes");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, RatedTvEpisodeResultsPage.class);
    }

    /**
     * Get the list of movies on an accounts watchlist.
     *
     * @return The watchlist of the user
     */
    public MovieResultsPage getWatchListMovies(Integer accountId, String sessionToken, String language, Integer page,
                                                 SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "watchlist/movies");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieResultsPage.class);
    }

    /**
     * Get the list of tv series on an accounts watchlist.
     *
     * @return The watchlist of the user
     */
    public TvSeriesResultsPage getWatchListTvSeries(Integer accountId, String sessionToken, String language, Integer page,
                                                    SortBy sortBy) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_ACCOUNT, accountId, "watchlist/tv");
        apiEndpoint.addQueryParam(PARAM_SESSION, sessionToken);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);
        apiEndpoint.addSortBy(sortBy);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesResultsPage.class);
    }

    /**
     * Needed to tell TMDB API about what type of id is provided.
     * e.g. see the <a href="https://developer.themoviedb.org/reference/account-add-favorite">documentation</a>
     */
    public enum MediaType {
        MOVIE, TV;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
