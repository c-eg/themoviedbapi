package info.movito.themoviedbapi.model.search;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface that is needed for /search/multi request
 * <p>
 * {@link SearchedMovie}, {@link SearchedPerson} and {@link SearchedTvSeries} implement this interface.</p>
 * <p>Each of them returns corresponding {@link MediaType}</p>
 *
 * @see info.movito.themoviedbapi.TmdbSearch#searchMulti(String, Boolean, String, Integer)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "media_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SearchedMovie.class, name = "movie"),
    @JsonSubTypes.Type(value = SearchedPerson.class, name = "person"),
    @JsonSubTypes.Type(value = SearchedTvSeries.class, name = "tv")
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

