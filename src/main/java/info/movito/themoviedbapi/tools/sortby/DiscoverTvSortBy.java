package info.movito.themoviedbapi.tools.sortby;

/**
 * The sort by methods allowed for the discover tv method.
 */
public enum DiscoverTvSortBy implements SortBy {
    FIRST_AIR_DATE_ASC("first_air_date.asc"),
    FIRST_AIR_DATE_DESC("first_air_date.desc"),
    NAME_ASC("name.asc"),
    NAME_DESC("name.desc"),
    ORIGINAL_NAME_ASC("original_name.asc"),
    ORIGINAL_NAME_DESC("original_name.desc"),
    POPULARITY_ASC("popularity.asc"),
    POPULARITY_DESC("popularity.desc"),
    VOTE_AVERAGE_ASC("vote_average.asc"),
    VOTE_AVERAGE_DESC("vote_average.desc"),
    VOTE_COUNT_ASC("vote_count.asc"),
    VOTE_COUNT_DESC("vote_count.desc");

    private final String value;

    DiscoverTvSortBy(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
