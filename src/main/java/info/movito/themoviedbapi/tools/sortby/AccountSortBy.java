package info.movito.themoviedbapi.tools.sortby;

/**
 * Some Tmdb methods allow you to specify the order that the results are returned in.
 */
public enum AccountSortBy implements SortBy {
    CREATED_AT_ASC("created_at.asc"),
    CREATED_AT_DESC("created_at.desc");

    private final String value;

    AccountSortBy(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
