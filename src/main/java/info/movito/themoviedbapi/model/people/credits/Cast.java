package info.movito.themoviedbapi.model.people.credits;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MovieCast.class, name = "movie"),
    @JsonSubTypes.Type(value = TvCast.class, name = "tv")
})
public interface Cast {
    /**
     * Used to determine the type of Cast object without {@code instanceof()} or {@code getClass}.
     */
    MediaType getMediaType();
}
