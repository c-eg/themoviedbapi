package uk.co.conoregan.themoviedbapi.model.core.multi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.Movie;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultiMovie extends Movie implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
