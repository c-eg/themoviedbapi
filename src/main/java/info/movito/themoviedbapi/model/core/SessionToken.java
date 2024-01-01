package info.movito.themoviedbapi.model.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A simple wrapper around the session token used for user authentication. Introduced to have a more strongly typed
 * api.
 *
 * @param sessionToken the session token.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public record SessionToken(String sessionToken) {
    public SessionToken {
        assert sessionToken != null;
    }
}
