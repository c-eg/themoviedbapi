package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.lists.ListDetails;
import info.movito.themoviedbapi.model.lists.ListItemStatus;
import info.movito.themoviedbapi.model.lists.MovieListCreationStatus;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.Utils;

import java.util.HashMap;

/**
 * The movie database api for lists. See the
 * <a href="https://developer.themoviedb.org/reference/list-add-movie">documentation</a> for more info.
 */
public class TmdbLists extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_LIST = "list";

    /**
     * Create a new TmdbLists instance to call the lists TMDb API methods.
     */
    public TmdbLists(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Add a movie to a list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-add-movie">documentation</a> for more info.</p>
     *
     * @param listId The list id.
     * @param sessionId The session id.
     * @param movieId The movie id.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus addMovie(Integer listId, String sessionId, Integer movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId, "add_item");
        apiUrl.addPathParam("session_id", sessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("media_id", movieId);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Use this method to check if an item has already been added to the list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-check-item-status">documentation</a> for more info.</p>
     *
     * @param listId The list id.
     * @param language optional - The language to query the results in. Default: en-US.
     * @param movieId optional - The movie id.
     * @return The list item status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ListItemStatus checkItemStatus(Integer listId, String language, Integer movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId, "item_status");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParam("movie_id", movieId);
        return mapJsonResult(apiUrl, ListItemStatus.class);
    }

    /**
     * <p>Clear all items from a list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-clear">documentation</a> for more info.</p>
     *
     * @param listId The list id.
     * @param sessionId The session id.
     * @param confirm Confirm the clear.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus clear(Integer listId, String sessionId, boolean confirm) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId, "clear");
        apiUrl.addPathParam("session_id", sessionId);
        apiUrl.addPathParam("confirm", confirm);
        return mapJsonResult(apiUrl, null, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Create a new list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-create">documentation</a> for more info.</p>
     *
     * @param sessionId The session id.
     * @param name The name of the list.
     * @param description The description of the list.
     * @param language optional - The language to query the results in. Default: en-US.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieListCreationStatus create(String sessionId, String name, String description, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST);
        apiUrl.addPathParam("session_id", sessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("description", description);
        body.put("language", language);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, MovieListCreationStatus.class);
    }

    /**
     * <p>Delete a list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-delete">documentation</a> for more info.</p>
     *
     * @param listId The list id.
     * @param sessionId The session id.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus delete(Integer listId, String sessionId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId);
        apiUrl.addPathParam("session_id", sessionId);
        return mapJsonResult(apiUrl, null, RequestType.DELETE, ResponseStatus.class);
    }

    /**
     * <p>Get the details of a list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-details">documentation</a> for more info.</p>
     *
     * @param listId The list id.
     * @param language optional - The language to query the results in. Default: en-US.
     * @param page optional - The page of results to return.
     * @return The movie list.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ListDetails getDetails(Integer listId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId);
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, ListDetails.class);
    }

    /**
     * <p>Remove a movie from a list.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/list-remove-movie">documentation</a> for more info.</p>
     *
     * @param listId The list id.
     * @param sessionId The session id.
     * @param movieId The movie id.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus removeMovie(Integer listId, String sessionId, Integer movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_LIST, listId, "remove_item");
        apiUrl.addPathParam("session_id", sessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("media_id", movieId);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }
}
