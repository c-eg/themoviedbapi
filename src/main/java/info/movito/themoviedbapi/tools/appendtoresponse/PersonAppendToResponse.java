package info.movito.themoviedbapi.tools.appendtoresponse;

/**
 * Person appendable responses.
 */
public enum PersonAppendToResponse implements AppendToResponse {
    CHANGES("changes"),
    COMBINED_CREDITS("combined_credits"),
    EXTERNAL_IDS("external_ids"),
    IMAGES("images"),
    LATEST("latest"),
    MOVIE_CREDITS("movie_credits"),
    TV_CREDITS("tv_credits"),
    TRANSLATIONS("translations");

    private final String value;

    PersonAppendToResponse(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
