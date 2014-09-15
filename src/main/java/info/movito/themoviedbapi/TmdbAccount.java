package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.MovieResults;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.core.StatusCode;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.MovieDbExceptionType;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TmdbAccount extends AbstractTmdbApi {

    public static final String PARAM_SESSION = "session_id";
    public static final String TMDB_METHOD_ACCOUNT = "account";


    TmdbAccount(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * Get the basic information for an account. You will need to have a valid session id.
     */
    public Account getAccount(String sessionId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT);

        apiUrl.addParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, Account.class);
    }


    /**
     * Get the lists that the movie belongs to
     *
     * @param movieId
     * @param sessionId
     * @param language
     * @param page
     */
    public List<MovieList> getUsersLists(int movieId, String sessionId, String language, Integer page, String... appendToResponse) {
        ApiUrl apiUrl = new ApiUrl(TmdbMovies.TMDB_METHOD_MOVIE, movieId, "lists");

        apiUrl.addParam(PARAM_SESSION, sessionId);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, page);
        }

        apiUrl.appendToResponse(appendToResponse);


        return mapJsonResult(apiUrl, MovieListResults.class).getResults();
    }


    static class MovieListResults extends ResultsPage<MovieList> {

    }




    public MovieResults getRatedMovies(String sessionId, int accountId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "rated_movies");
        apiUrl.addParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, MovieResults.class);
    }




    /**
     * This method lets users rate a movie.
     * <p/>
     * A valid session id is required.
     *
     * @param sessionId
     * @param movieId
     * @param rating
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public boolean postMovieRating(String sessionId, Integer movieId, Integer rating) {
        ApiUrl apiUrl = new ApiUrl(TmdbMovies.TMDB_METHOD_MOVIE, movieId, "rating");

        apiUrl.addParam(PARAM_SESSION, sessionId);

        if (rating < 0 || rating > 10) {
            throw new MovieDbException(MovieDbExceptionType.UNKNOWN_CAUSE, "rating out of range");
        }

        String jsonBody = Utils.convertToJson(jsonMapper, Collections.singletonMap("value", rating));

        return mapJsonResult(apiUrl, StatusCode.class, jsonBody).getStatusCode() == 12;
    }


    public MovieResults getFavoriteMovies(String sessionId, int accountId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/movies");
        apiUrl.addParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, MovieResults.class);
    }


    public TvResults getFavoriteSeries(String sessionId, int accountId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite/tv");
        apiUrl.addParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, TvResults.class);
    }


    public StatusCode changeFavoriteStatus(String sessionId, int accountId, Integer movieId, MediaType mediaType, boolean isFavorite) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "favorite");

        apiUrl.addParam(PARAM_SESSION, sessionId);

        HashMap<String, Object> body = new HashMap<String, Object>();

        body.put("media_type", mediaType.toString());
        body.put("media_id", movieId);
        body.put("favorite", isFavorite);

        String jsonBody = Utils.convertToJson(jsonMapper, body);

        return mapJsonResult(apiUrl, StatusCode.class, jsonBody);
    }


    /**
     * Get the list of movies on an accounts watchlist.
     *
     * @return The watchlist of the user
     */
    public MovieResults getWatchListMovies(String sessionId, int accountId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/movies");
        apiUrl.addParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, MovieResults.class);
    }


    public TvResults getWatchListSeries(String sessionId, int accountId) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist/tv");
        apiUrl.addParam(PARAM_SESSION, sessionId);

        return mapJsonResult(apiUrl, TvResults.class);
    }


    /**
     * Add a movie to an account's watch list.
     */
    public StatusCode addToWatchList(String sessionId, int accountId, Integer movieId, MediaType mediaType) {
        return modifyWatchList(sessionId, accountId, movieId, true, mediaType);
    }


    /**
     * Remove a movie from an account's watch list.
     */
    public StatusCode removeFromWatchList(String sessionId, int accountId, Integer movieId, MediaType mediaType) {
        return modifyWatchList(sessionId, accountId, movieId, false, mediaType);
    }


    private StatusCode modifyWatchList(String sessionId, int accountId, Integer movieId, boolean isWatched, MediaType mediaType) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_ACCOUNT, accountId, "watchlist");

        apiUrl.addParam(PARAM_SESSION, sessionId);

        HashMap<String, Object> body = new HashMap<String, Object>();

        body.put("media_type", mediaType.toString());
        body.put("media_id", movieId);
        body.put("watchlist", isWatched);

        String jsonBody = Utils.convertToJson(jsonMapper, body);

        return mapJsonResult(apiUrl, StatusCode.class, jsonBody);
    }


    /**
     * needed to tell tmdb api about what type of id is provided. E.g. see http://docs.themoviedb.apiary.io/reference/account/accountidwatchlist
     */
    // note http://stackoverflow.com/questions/8143995/should-java-member-enum-types-be-capitalized
    static enum MediaType {
        MOVIE, TV;


        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }


    }
}
