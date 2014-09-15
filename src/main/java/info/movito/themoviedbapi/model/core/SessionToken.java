package info.movito.themoviedbapi.model.core;

/**
 * A simple wrapper around the session token used for user authentication. Introduced to have a more strongly typed
 * api.
 */
public class SessionToken {

    private final String sessionToken;


    public SessionToken(String sessionToken) {
        assert sessionToken != null;
        this.sessionToken = sessionToken;
    }


    @Override
    public String toString() {
        return sessionToken;
    }


    @Override
    public int hashCode() {
        return sessionToken.hashCode();
    }


    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return sessionToken.equals(obj.toString());
    }
}
