package info.movito.themoviedbapi.model.search;

import info.movito.themoviedbapi.model.core.Movie;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultiMovie extends Movie implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
