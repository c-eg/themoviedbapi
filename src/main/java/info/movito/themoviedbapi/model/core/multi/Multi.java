package info.movito.themoviedbapi.model.core.multi;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface that is needed for /search/multi request
 * <p>
 * {@link MultiMovie}, {@link MultiPerson} and {@link MultiTvSeries} implement this interface.</p>
 * <p>Each of them returns corresponding {@link MediaType}</p>
 *
 * @see info.movito.themoviedbapi.TmdbSearch#searchMulti(String, Boolean, String, Integer)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "media_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MultiMovie.class, name = "movie"),
    @JsonSubTypes.Type(value = MultiPerson.class, name = "person"),
    @JsonSubTypes.Type(value = MultiTvSeries.class, name = "tv")
})
public interface Multi {
    /**
     * Used to determine type Multi object without {@code instanceof()} or {@code getClass}.
     */
    MediaType getMediaType();

    /**
     * The type of media.
     */
    enum MediaType {
        MOVIE,
        PERSON,
        TV_SERIES
    }
}

