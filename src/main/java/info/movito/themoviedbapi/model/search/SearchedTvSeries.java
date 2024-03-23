package info.movito.themoviedbapi.model.search;

import info.movito.themoviedbapi.model.core.TvSeries;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchedTvSeries extends TvSeries implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.TV_SERIES;
    }
}
