package uk.co.conoregan.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "media_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MovieMedia.class, name = "movie"),
    @JsonSubTypes.Type(value = TvMedia.class, name = "tv")
})
public interface Media {
    /**
     * Used to determine the type of Media object without {@code instanceof()} or {@code getClass}.
     */
    MediaType getMediaType();
}
