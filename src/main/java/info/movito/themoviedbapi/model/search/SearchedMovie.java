package info.movito.themoviedbapi.model.search;

import info.movito.themoviedbapi.model.core.Movie;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchedMovie extends Movie implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
