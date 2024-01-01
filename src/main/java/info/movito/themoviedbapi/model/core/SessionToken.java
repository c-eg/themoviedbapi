package info.movito.themoviedbapi.model.core;

/**
 * A simple wrapper around the session token used for user authentication. Introduced to have a more strongly typed
 * api.
 *
 * @param sessionToken the session token.
 */
public record SessionToken(String sessionToken) {
    public SessionToken {
        assert sessionToken != null;
    }
}
