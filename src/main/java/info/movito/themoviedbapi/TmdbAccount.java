package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;

import java.util.Collections;
import java.util.HashMap;

import static info.movito.themoviedbapi.TmdbTV.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.TmdbTvEpisodes.TMDB_METHOD_TV_EPISODE;
import static info.movito.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;

/**
 * The movie database api for accounts. See the
 * <a href="https://developer.themoviedb.org/reference/account-details">documentation</a> for more info.
 */
public class TmdbAccount extends AbstractTmdbApi {
    public static final String PARAM_SESSION = "session_id";

    public static final String TMDB_METHOD_ACCOUNT = "account";

    /**
     * Create a new TmdbAccount instance to call the account related TMDb API methods.
     */
    TmdbAccount(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get the basic information for an account. You will need to have a valid session id.
     */
    public Account getAccount(SessionToken sessionToken) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT);

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        return mapJsonResult(apiUrl, Account.class);
    }

    /**
     * Get the lists that as user has created.
     */
    public MovieListResultsPage getLists(SessionToken sessionToken, AccountID accountId, String language,
                                         Integer page) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "lists");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieListResultsPage.class);
    }

    /**
     * Get the rated movies from the account.
     */
    public MovieResultsPage getRatedMovies(SessionToken sessionToken, AccountID accountId, Integer page) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/movies");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * Get the rated tv shows from the account.
     */
    public TvResultsPage getRatedTvSeries(SessionToken sessionToken, AccountID accountId, Integer page) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/tv");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Get the rated tv episodes from the account.
     */
    public TvEpisodesResultsPage getRatedEpisodes(SessionToken sessionToken, AccountID accountId, Integer page)
        throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/tv/episodes");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvEpisodesResultsPage.class);
    }

    /**
     * This method lets users rate a movie.
     * <p>
     * A valid session id is required.
     */
    public boolean postMovieRating(SessionToken sessionToken, Integer movieId, Integer rating) throws TmdbResponseException {
        return postRatingInternal(sessionToken, rating, new ApiUrl(TmdbMovies.TMDB_METHOD_MOVIE, movieId, "rating"));
    }

    /**
     * This method lets users rate a tv series.
     * <p>
     * A valid session id is required.
     */
    public boolean postTvSeriesRating(SessionToken sessionToken, Integer movieId, Integer rating) throws TmdbResponseException {
        return postRatingInternal(sessionToken, rating, new ApiUrl(TmdbTV.TMDB_METHOD_TV, movieId, "rating"));
    }

    /**
     * This method lets users rate a tv episode.
     */
    public boolean postTvExpisodeRating(SessionToken sessionToken, Integer seriesId, Integer seasonNumber,
                                        Integer episodeNumber, Integer rating) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId,
            TMDB_METHOD_TV_SEASON, seasonNumber,
            TMDB_METHOD_TV_EPISODE, episodeNumber,
            "rating"
        );

        return postRatingInternal(sessionToken, rating, apiUrl);
    }

    private boolean postRatingInternal(SessionToken sessionToken, Integer rating, ApiUrl apiUrl) throws TmdbResponseException {
        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        if (rating < 0 || rating > 10) {
            throw new MovieDbException("rating out of range");
        }

        String jsonBody = Utils.convertToJson(getObjectMapper(), Collections.singletonMap("value", rating));

        return mapJsonResult(apiUrl, jsonBody, ResponseStatus.class).getStatusCode() == TmdbResponseCode.ITEM_UPDATED.getTmdbCode();
    }

    /**
     * Get favourites movies from the account.
     */
    public MovieResultsPage getFavoriteMovies(SessionToken sessionToken, AccountID accountId) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/movies");
        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * Get the favorite tv shows from the account.
     */
    public TvResultsPage getFavoriteSeries(SessionToken sessionToken, AccountID accountId, Integer page) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/tv");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Remove a movie from an account's favorites list.
     */
    public ResponseStatus addFavorite(SessionToken sessionToken, AccountID accountId, Integer movieId,
                                      MediaType mediaType) throws TmdbResponseException {
        return changeFavoriteStatus(sessionToken, accountId, movieId, mediaType, true);
    }

    /**
     * Remove a movie from an account's favorites list.
     */
    public ResponseStatus removeFavorite(SessionToken sessionToken, AccountID accountId, Integer movieId,
                                         MediaType mediaType) throws TmdbResponseException {
        return changeFavoriteStatus(sessionToken, accountId, movieId, mediaType, false);
    }

    private ResponseStatus changeFavoriteStatus(SessionToken sessionToken, AccountID accountId, Integer movieId,
                                                MediaType mediaType, boolean isFavorite) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        HashMap<String, Object> body = new HashMap<>();

        body.put("media_type", mediaType.toString());
        body.put("media_id", movieId);
        body.put("favorite", isFavorite);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);

        return mapJsonResult(apiUrl, jsonBody, ResponseStatus.class);
    }

    /**
     * Get the list of movies on an accounts watchlist.
     *
     * @return The watchlist of the user
     */
    public MovieResultsPage getWatchListMovies(SessionToken sessionToken, AccountID accountId, Integer page) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/movies");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * Get the list of tv series on an accounts watchlist.
     *
     * @return The watchlist of the user
     */
    public TvResultsPage getWatchListSeries(SessionToken sessionToken, AccountID accountId, Integer page) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/tv");
        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Add a movie to an account's watch list.
     */
    public ResponseStatus addToWatchList(SessionToken sessionToken, AccountID accountId, Integer movieId,
                                         MediaType mediaType) throws TmdbResponseException {
        return modifyWatchList(sessionToken, accountId, movieId, mediaType, true);
    }

    /**
     * Remove a movie from an account's watch list.
     */
    public ResponseStatus removeFromWatchList(SessionToken sessionToken, AccountID accountId, Integer movieId,
                                              MediaType mediaType) throws TmdbResponseException {
        return modifyWatchList(sessionToken, accountId, movieId, mediaType, false);
    }

    private ResponseStatus modifyWatchList(SessionToken sessionToken, AccountID accountId, Integer movieId,
                                           MediaType mediaType, boolean isWatched) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist");

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        HashMap<String, Object> body = new HashMap<>();

        body.put("media_type", mediaType.toString());
        body.put("media_id", movieId);
        body.put("watchlist", isWatched);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);

        return mapJsonResult(apiUrl, jsonBody, ResponseStatus.class);
    }

    /**
     * needed to tell tmdb api about what type of id is provided. E.g. see http://docs.themoviedb.apiary.io/reference/account/accountidwatchlist
     */
    // note http://stackoverflow.com/questions/8143995/should-java-member-enum-types-be-capitalized
    public enum MediaType {
        MOVIE, TV;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    /**
     * Movies List Results Page.
     */
    public static class MovieListResultsPage extends ResultsPage<MovieList> {

    }
}
