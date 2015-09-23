package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;

import java.util.Collections;
import java.util.HashMap;

import static info.movito.themoviedbapi.TmdbTV.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.TmdbTvEpisodes.TMDB_METHOD_TV_EPISODE;
import static info.movito.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;


public class TmdbAccount extends AbstractTmdbApi {

    public static final String PARAM_SESSION = "session_id";
    public static final String TMDB_METHOD_ACCOUNT = "account";


    TmdbAccount(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * Get the basic information for an account. You will need to have a valid session id.
     */
    public Account getAccount(SessionToken sessionToken) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT);

        apiUrl.addParam(PARAM_SESSION, sessionToken);

        return mapJsonResult(apiUrl, Account.class);
    }


    /**
     * Get the lists that as user has created.
     */
    public MovieListResultsPage getLists(SessionToken sessionToken, AccountID accountId, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "lists");

        apiUrl.addParam(PARAM_SESSION, sessionToken);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieListResultsPage.class);
    }


    public static class MovieListResultsPage extends ResultsPage<MovieList> {

    }


    public MovieResultsPage getRatedMovies(SessionToken sessionToken, AccountID accountId, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/movies");

        apiUrl.addParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);


        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }


    public TvResultsPage getRatedTvSeries(SessionToken sessionToken, AccountID accountId, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/tv");

        apiUrl.addParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);


        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvEpisodesResultsPage getRatedEpisodes(SessionToken sessionToken, AccountID accountId, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated/tv/episodes");

        apiUrl.addParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);


        return mapJsonResult(apiUrl, TvEpisodesResultsPage.class);
    }


    /**
     * This method lets users rate a movie.
     * <p/>
     * A valid session id is required.
     *
     * @param sessionToken
     * @param movieId
     * @param rating
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public boolean postMovieRating(SessionToken sessionToken, Integer movieId, Integer rating) {
        return postRatingInternal(sessionToken, rating, new ApiUrl(TmdbMovies.TMDB_METHOD_MOVIE, movieId, "rating"));
    }


    public boolean postTvSeriesRating(SessionToken sessionToken, Integer movieId, Integer rating) {
        return postRatingInternal(sessionToken, rating, new ApiUrl(TmdbTV.TMDB_METHOD_TV, movieId, "rating"));
    }


    public boolean postTvExpisodeRating(SessionToken sessionToken, Integer seriesId, Integer seasonNumber, Integer episodeNumber, Integer rating) {
        ApiUrl apiUrl = new ApiUrl(
                TMDB_METHOD_TV, seriesId,
                TMDB_METHOD_TV_SEASON, seasonNumber,
                TMDB_METHOD_TV_EPISODE, episodeNumber,
                "rating"
        );

        return postRatingInternal(sessionToken, rating, apiUrl);
    }


    private boolean postRatingInternal(SessionToken sessionToken, Integer rating, ApiUrl apiUrl) {
        apiUrl.addParam(PARAM_SESSION, sessionToken);

        if (rating < 0 || rating > 10) {
            throw new MovieDbException("rating out of range");
        }

        String jsonBody = Utils.convertToJson(jsonMapper, Collections.singletonMap("value", rating));

        return mapJsonResult(apiUrl, ResponseStatus.class, jsonBody).getStatusCode() == 12;
    }


    public MovieResultsPage getFavoriteMovies(SessionToken sessionToken, AccountID accountId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/movies");
        apiUrl.addParam(PARAM_SESSION, sessionToken);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }


    public TvResultsPage getFavoriteSeries(SessionToken sessionToken, AccountID accountId, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/tv");

        apiUrl.addParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    /**
     * Remove a movie from an account's favorites list.
     */
    public ResponseStatus addFavorite(SessionToken sessionToken, AccountID accountId, Integer movieId, MediaType mediaType) {
        return changeFavoriteStatus(sessionToken, accountId, movieId, mediaType, true);
    }


    /**
     * Remove a movie from an account's favorites list.
     */
    public ResponseStatus removeFavorite(SessionToken sessionToken, AccountID accountId, Integer movieId, MediaType mediaType) {
        return changeFavoriteStatus(sessionToken, accountId, movieId, mediaType, false);
    }


    private ResponseStatus changeFavoriteStatus(SessionToken sessionToken, AccountID accountId, Integer movieId, MediaType mediaType, boolean isFavorite) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite");

        apiUrl.addParam(PARAM_SESSION, sessionToken);

        HashMap<String, Object> body = new HashMap<String, Object>();

        body.put("media_type", mediaType.toString());
        body.put("media_id", movieId);
        body.put("favorite", isFavorite);

        String jsonBody = Utils.convertToJson(jsonMapper, body);

        return mapJsonResult(apiUrl, ResponseStatus.class, jsonBody);
    }


    /**
     * Get the list of movies on an accounts watchlist.
     *
     * @return The watchlist of the user
     */
    public MovieResultsPage getWatchListMovies(SessionToken sessionToken, AccountID accountId, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/movies");

        apiUrl.addParam(PARAM_SESSION, sessionToken);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }


    public TvResultsPage getWatchListSeries(SessionToken sessionToken, AccountID accountId, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/tv");
        apiUrl.addParam(PARAM_SESSION, sessionToken);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    /**
     * Add a movie to an account's watch list.
     */
    public ResponseStatus addToWatchList(SessionToken sessionToken, AccountID accountId, Integer movieId, MediaType mediaType) {
        return modifyWatchList(sessionToken, accountId, movieId, mediaType, true);
    }


    /**
     * Remove a movie from an account's watch list.
     */
    public ResponseStatus removeFromWatchList(SessionToken sessionToken, AccountID accountId, Integer movieId, MediaType mediaType) {
        return modifyWatchList(sessionToken, accountId, movieId, mediaType, false);
    }


    private ResponseStatus modifyWatchList(SessionToken sessionToken, AccountID accountId, Integer movieId, MediaType mediaType, boolean isWatched) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist");

        apiUrl.addParam(PARAM_SESSION, sessionToken);

        HashMap<String, Object> body = new HashMap<String, Object>();

        body.put("media_type", mediaType.toString());
        body.put("media_id", movieId);
        body.put("watchlist", isWatched);

        String jsonBody = Utils.convertToJson(jsonMapper, body);

        return mapJsonResult(apiUrl, ResponseStatus.class, jsonBody);
    }


    /**
     * needed to tell tmdb api about what type of id is provided. E.g. see http://docs.themoviedb.apiary.io/reference/account/accountidwatchlist
     */
    // note http://stackoverflow.com/questions/8143995/should-java-member-enum-types-be-capitalized
    public static enum MediaType {
        MOVIE, TV;


        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }


    }
}
