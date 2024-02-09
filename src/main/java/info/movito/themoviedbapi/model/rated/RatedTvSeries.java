package info.movito.themoviedbapi.model.rated;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.TvSeries;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RatedTvSeries extends TvSeries {
    @JsonProperty("rating")
    private Double rating;
}
