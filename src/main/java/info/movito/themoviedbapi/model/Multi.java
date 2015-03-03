package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import info.movito.themoviedbapi.tools.MultiTypeIdResolver;

/**
 * Interface that is needed for /search/multi request
 * <p>
 * {@link info.movito.themoviedbapi.model.MovieDb}, {@link info.movito.themoviedbapi.model.people.Person} and
 * {@link info.movito.themoviedbapi.model.tv.TvSeries} implement this interface.</p>
 * <p>Each of them returns corresponding {@link info.movito.themoviedbapi.model.Multi.MediaType}</p>
 * @see info.movito.themoviedbapi.TmdbSearch#searchMulti(String, String, Integer)
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CUSTOM, include=JsonTypeInfo.As.PROPERTY, property="media_type")
@JsonTypeIdResolver(MultiTypeIdResolver.class)
public interface Multi {

    /**
     * Used to determine type Multi object without {@code instanceof()} or {@code getClass}
     */
    public enum MediaType {
        MOVIE,
        PERSON,
        TV_SERIES;
    }

    public MediaType getMediaType();
}
