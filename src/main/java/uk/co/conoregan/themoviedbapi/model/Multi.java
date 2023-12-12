package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import uk.co.conoregan.themoviedbapi.api.TmdbSearch;
import uk.co.conoregan.themoviedbapi.model.people.Person;
import uk.co.conoregan.themoviedbapi.model.people.PersonPeople;
import uk.co.conoregan.themoviedbapi.model.tv.TvSeriesDb;

/**
 * Interface that is needed for /search/multi request
 * <p>
 * {@link MovieDb}, {@link Person} and {@link TvSeriesDb} implement this interface.</p>
 * <p>Each of them returns corresponding {@link MediaType}</p>
 *
 * @see TmdbSearch#searchMulti(String, String, Integer)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "media_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MovieDb.class, name = "movie"),
    @JsonSubTypes.Type(value = PersonPeople.class, name = "person"),
    @JsonSubTypes.Type(value = TvSeriesDb.class, name = "tv")})
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

