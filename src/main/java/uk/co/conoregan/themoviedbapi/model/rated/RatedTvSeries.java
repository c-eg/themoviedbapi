package uk.co.conoregan.themoviedbapi.model.rated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.account.TvSeries;

@Getter
@Setter
public class RatedTvSeries extends TvSeries {
    @JsonProperty("rating")
    private Double rating;
}
