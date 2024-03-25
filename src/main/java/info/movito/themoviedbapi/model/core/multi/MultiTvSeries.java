package info.movito.themoviedbapi.model.core.multi;

import info.movito.themoviedbapi.model.core.TvSeries;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultiTvSeries extends TvSeries implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.TV_SERIES;
    }
}
