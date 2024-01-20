package info.movito.themoviedbapi.tools.sortby;

/**
 * Some Tmdb methods allow you to specify the order that the results are returned in.
 * Should be implemented by enums to only allow search criteria that are supported by the API.
 */
public interface SortBy {
    /**
     * Returns the value of the enum as it is used by the API. e.g. "created_at.asc"
     *
     * @return the value of the enum as it is used by the API.
     */
    String getValue();
}
