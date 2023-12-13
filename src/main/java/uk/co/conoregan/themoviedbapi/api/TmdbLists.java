package uk.co.conoregan.themoviedbapi.api;

import org.apache.commons.lang3.StringUtils;
import uk.co.conoregan.themoviedbapi.model.ListItemStatus;
import uk.co.conoregan.themoviedbapi.model.MovieList;
import uk.co.conoregan.themoviedbapi.model.MovieListCreationStatus;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.util.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_LIST, listId);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieList.class);
    }

    /**
     * This method lets users create a new list. A valid session id is required.
     *
     * @return The list id
     */
    public String createList(String sessionToken, String name, String description) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_LIST);

        apiEndpoint.addPathParam(TmdbAccount.PARAM_SESSION, sessionToken);

        HashMap<String, String> body = new HashMap<>();
        body.put("name", StringUtils.trimToEmpty(name));
        body.put("description", StringUtils.trimToEmpty(description));

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, MovieListCreationStatus.class).getListId();
    }

    /**
     * Check to see if a movie ID is already added to a list.
     *
     * @return true if the movie is on the list
     */
    public boolean isMovieOnList(String listId, Integer movieId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_LIST, listId, "item_status");
        apiEndpoint.addPathParam("movie_id", movieId);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ListItemStatus.class).getItemPresent();
    }

    /**
     * This method lets users add new movies to a list that they created. A valid session id is required.
     *
     * @return true if the movie is on the list
     */
    public ResponseStatus addMovieToList(String sessionToken, String listId, Integer movieId) throws TmdbException {
        return modifyMovieList(sessionToken, listId, movieId, "add_item");
    }

    /**
     * This method lets users remove movies from a list that they created. A valid session id is required.
     *
     * @return true if the movie is on the list
     */
    public ResponseStatus removeMovieFromList(String sessionToken, String listId, Integer movieId) throws TmdbException {
        return modifyMovieList(sessionToken, listId, movieId, "remove_item");
    }

    private ResponseStatus modifyMovieList(String sessionToken, String listId, Integer movieId,
                                           String operation) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_LIST, listId, operation);
        apiEndpoint.addPathParam(TmdbAccount.PARAM_SESSION, sessionToken);

        Map<String, Object> body = Collections.singletonMap("media_id", movieId + "");
        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        String responseBody = makePostRequest(apiEndpoint, jsonBody);
        return mapJsonResult(responseBody, ResponseStatus.class);
    }

    /**
     * This method lets users delete a list that they created. A valid session id is required.
     */
    public ResponseStatus deleteMovieList(String sessionToken, String listId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_LIST, listId);
        apiEndpoint.addPathParam(TmdbAccount.PARAM_SESSION, sessionToken);

        String responseBody = makeDeleteRequest(apiEndpoint);
        return mapJsonResult(responseBody, ResponseStatus.class);
    }
}
