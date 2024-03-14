package info.movito.themoviedbapi.tools.appendtoresponse;

/**
 * Tv Series appendable responses.
 */
public enum TvSeriesAppendToResponse implements AppendToResponse {
    ACCOUNT_STATES("account_states"),
    AGGREGATE_CREDITS("aggregate_credits"),
    ALTERNATIVE_TITLES("alternative_titles"),
    CHANGES("changes"),
    CONTENT_RATINGS("content_ratings"),
    CREDITS("credits"),
    EPISODE_GROUPS("episode_groups"),
    EXTERNAL_IDS("external_ids"),
    IMAGES("images"),
    KEYWORDS("keywords"),
    LISTS("lists"),
    RECOMMENDATIONS("recommendations"),
    REVIEWS("reviews"),
    SCREENED_THEATRICALLY("screened_theatrically"),
    SIMILAR("similar"),
    TRANSLATIONS("translations"),
    VIDEOS("videos"),
    WATCH_PROVIDERS("watch/providers");

    private final String value;

    TvSeriesAppendToResponse(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
