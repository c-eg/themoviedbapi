package info.movito.themoviedbapi.tools.appendtoresponse;

/**
 * Tv Seasons appendable responses.
 */
public enum TvSeasonsAppendToResponse implements AppendToResponse {
    ACCOUNT_STATES("account_states"),
    AGGREGATE_CREDITS("aggregate_credits"),
    CREDITS("credits"),
    EXTERNAL_IDS("external_ids"),
    IMAGES("images"),
    TRANSLATIONS("translations"),
    VIDEOS("videos"),
    WATCH_PROVIDERS("watch/providers");

    private final String value;

    TvSeasonsAppendToResponse(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
