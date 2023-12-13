package uk.co.conoregan.themoviedbapi.model.rated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.account.TvSeries;

@Data
@EqualsAndHashCode(callSuper = true)
public class RatedTvSeries extends TvSeries {
    @JsonProperty("rating")
    private Double rating;
}
