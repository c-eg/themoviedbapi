package info.movito.themoviedbapi.model.tv.episodegroups;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EpisodeGroupType {
    ORIGINAL_AIR_DATE,
    ABSOLUTE,
    DVD,
    DIGITAL,
    STORY_ARC,
    PRODUCTION,
    TV;

    @JsonValue
    public int toValue() {
        return ordinal() + 1;  // ids start at 1
    }
}
