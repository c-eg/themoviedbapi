package info.movito.themoviedbapi;

import java.util.HashMap;

import info.movito.themoviedbapi.model.account.Account;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.sortby.AccountSortBy;
import info.movito.themoviedbapi.util.JsonUtil;

/**
 * The movie database api for accounts. See the
 * <a href="https://developer.themoviedb.org/reference/account-details">documentation</a> for more info.
 */
public class TmdbAccount extends AbstractTmdbApi {
    public static final String PARAM_SESSION = "session_id";
    protected static final String TMDB_METHOD_ACCOUNT = "account";

    /**
     * Create a new TmdbAccount instance to call the account related TMDb API methods.
     */
    TmdbAccount(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the basic information for an account. You will need to have a valid session id.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-details">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @return The account details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Account getDetails(Integer accountId, String sessionId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId);
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, Account.class);
    }

    /**
     * <p>Add media to an account's favorites list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-add-favorite">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param mediaId   the id of the media to add to the favorites list.
     * @param mediaType the type of media to add to the favorites list.
     * @return The status of the request.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus addFavorite(Integer accountId, String sessionId, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeFavoriteStatus(accountId, sessionId, mediaId, mediaType, true);
    }

    /**
     * <p>Remove media to an account's favorites list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-add-favorite">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param mediaId   the id of the media to remove from the favorites list.
     * @param mediaType the type of media to remove from the favorites list.
     * @return The status of the request.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus removeFavorite(Integer accountId, String sessionId, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeFavoriteStatus(accountId, sessionId, mediaId, mediaType, false);
    }

    private ResponseStatus changeFavoriteStatus(Integer accountId, String sessionId, Integer mediaId, MediaType mediaType,
                                                boolean isFavorite) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("media_type", mediaType.toString());
        body.put("media_id", mediaId);
        body.put("favorite", isFavorite);

        String jsonBody = JsonUtil.convertToJson(getObjectMapper(), body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Add media to an account's watch list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-add-to-watchlist">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param mediaId   the id of the media to add to the watch list.
     * @param mediaType the type of media to add to the watch list.
     * @return The status of the request.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus addToWatchList(Integer accountId, String sessionId, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeWatchListStatus(accountId, sessionId, mediaId, mediaType, true);
    }

    /**
     * <p>Remove media to an account's watch list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-add-to-watchlist">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param mediaId   the id of the media to remove from the watch list.
     * @param mediaType the type of media to remove from the watch list.
     * @return The status of the request.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus removeFromWatchList(Integer accountId, String sessionId, Integer mediaId, MediaType mediaType)
        throws TmdbException {
        return changeWatchListStatus(accountId, sessionId, mediaId, mediaType, false);
    }

    private ResponseStatus changeWatchListStatus(Integer accountId, String sessionId, Integer mediaId, MediaType mediaType,
                                                 boolean isWatchList) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("media_type", mediaType.toString());
        body.put("media_id", mediaId);
        body.put("watchlist", isWatchList);

        String jsonBody = JsonUtil.convertToJson(getObjectMapper(), body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Get the favorite movies from the account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-get-favorites">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The favorite movies of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getFavoriteMovies(Integer accountId, String sessionId, String language, Integer page,
                                              AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/movies");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get the favorite tv shows from the account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-favorite-tv">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The favorite tv series of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getFavoriteTv(Integer accountId, String sessionId, String language, Integer page,
                                             AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/tv");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }

    /**
     * <p>Get the lists that as user has created.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-lists">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param page      nullable - The page of results to return. Default: 1.
     * @return The lists of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieListResultsPage getLists(Integer accountId, String sessionId, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "lists");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieListResultsPage.class);
    }

    /**
     * <p>Get the rated movies from the account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-rated-movies">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The rated movies of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RatedMovieResultsPage getRatedMovies(int accountId, String sessionId, String language, Integer page,
                                                AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/movies");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, RatedMovieResultsPage.class);
    }

    /**
     * <p>Get the rated tv shows from the account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-rated-tv">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The rated tv series of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RatedTvSeriesResultsPage getRatedTvSeries(int accountId, String sessionId, String language, Integer page,
                                                     AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/tv");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, RatedTvSeriesResultsPage.class);
    }

    /**
     * <p>Get the rated tv episodes from the account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-rated-tv-episodes">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The rated tv episodes of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RatedTvEpisodeResultsPage getRatedTvEpisodes(int accountId, String sessionId, String language, Integer page,
                                                        AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/tv/episodes");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, RatedTvEpisodeResultsPage.class);
    }

    /**
     * <p>Get the list of movies on an accounts watchlist.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-watchlist-movies">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The movies in the account's watchlist
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getWatchListMovies(Integer accountId, String sessionId, String language, Integer page,
                                               AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/movies");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get the list of tv series on an accounts watchlist.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/account-watchlist-tv">documentation</a> for more info.</p>
     *
     * @param accountId The account id of the user.
     * @param sessionId nullable - The session id of the user.
     * @param language  nullable - The language to query the results in. Default: en-US.
     * @param page      nullable - The page of results to return. Default: 1.
     * @param sortBy    nullable - The sort order of the results.
     * @return The tv series in the account's watchlist
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getWatchListTvSeries(Integer accountId, String sessionId, String language, Integer page,
                                                    AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/tv");
        apiUrl.addQueryParam(PARAM_SESSION, sessionId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
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
