package info.movito.themoviedbapi.tools.appendtoresponse;

/**
 * Tv Episodes appendable responses.
 */
public enum TvEpisodesAppendToResponse implements AppendToResponse {
    ACCOUNT_STATES("account_states"),
    CREDITS("credits"),
    EXTERNAL_IDS("external_ids"),
    IMAGES("images"),
    TRANSLATIONS("translations"),
    VIDEOS("videos");

    private final String value;

    TvEpisodesAppendToResponse(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
