package info.movito.themoviedbapi.tools.appendtoresponse;

/**
 * Some Tmdb methods allow you to append additional namespaces to the result.
 * Should be implemented by enums to only allow search criteria that are supported by the API.
 */
public interface AppendToResponse {
    /**
     * Returns the value of the enum as it is used by the API. e.g. "combined_credits"
     *
     * @return the value of the enum as it is used by the API.
     */
    String getValue();
}
