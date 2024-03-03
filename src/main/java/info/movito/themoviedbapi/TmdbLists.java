package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.ListItemStatus;
import info.movito.themoviedbapi.model.movies.MovieList;
import info.movito.themoviedbapi.model.MovieListCreationStatus;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;

/**
 * The movie database api for lists. See the
 * <a href="https://developer.themoviedb.org/reference/list-add-movie">documentation</a> for more info.
 */
public class TmdbLists extends AbstractTmdbApi {
    public static final String TMDB_METHOD_LIST = "list";

    /**
     * Create a new TmdbLists instance to call the lists TMDb API methods.
     */
    public TmdbLists(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get a list by its ID.
     *
     * @return The list and its items
     */
    public MovieList getList(String listId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId);

        return mapJsonResult(apiUrl, MovieList.class);
    }

    /**
     * This method lets users create a new list. A valid session id is required.
     *
     * @return The list id
     */
    public String createList(SessionToken sessionToken, String name, String description) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST);

        apiUrl.addPathParam(TmdbAccount.PARAM_SESSION, sessionToken);

        HashMap<String, String> body = new HashMap<>();
        body.put("name", StringUtils.trimToEmpty(name));
        body.put("description", StringUtils.trimToEmpty(description));

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);

        return mapJsonResult(apiUrl, jsonBody, MovieListCreationStatus.class).getListId();
    }

    /**
     * Check to see if a movie ID is already added to a list.
     *
     * @return true if the movie is on the list
     */
    public boolean isMovieOnList(String listId, Integer movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId, "item_status");

        apiUrl.addPathParam("movie_id", movieId);

        return mapJsonResult(apiUrl, ListItemStatus.class).isItemPresent();
    }

    /**
     * This method lets users add new movies to a list that they created. A valid session id is required.
     *
     * @return true if the movie is on the list
     */
    public ResponseStatus addMovieToList(SessionToken sessionToken, String listId, Integer movieId) throws TmdbException {
        return modifyMovieList(sessionToken, listId, movieId, "add_item");
    }

    /**
     * This method lets users remove movies from a list that they created. A valid session id is required.
     *
     * @return true if the movie is on the list
     */
    public ResponseStatus removeMovieFromList(SessionToken sessionToken, String listId, Integer movieId) throws TmdbException {
        return modifyMovieList(sessionToken, listId, movieId, "remove_item");
    }

    private ResponseStatus modifyMovieList(SessionToken sessionToken, String listId, Integer movieId,
                                           String operation) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId, operation);

        apiUrl.addPathParam(TmdbAccount.PARAM_SESSION, sessionToken);

        String jsonBody = Utils.convertToJson(getObjectMapper(), Collections.singletonMap("media_id", movieId + ""));

        return mapJsonResult(apiUrl, jsonBody, ResponseStatus.class);
    }

    /**
     * This method lets users delete a list that they created. A valid session id is required.
     */
    public ResponseStatus deleteMovieList(SessionToken sessionToken, String listId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId);

        apiUrl.addPathParam(TmdbAccount.PARAM_SESSION, sessionToken);

        return mapJsonResult(apiUrl, null, RequestType.DELETE, ResponseStatus.class);
    }
}
