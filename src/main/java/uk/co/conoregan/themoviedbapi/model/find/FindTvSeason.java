package uk.co.conoregan.themoviedbapi.model.find;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.tv.core.TvSeason;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindTvSeason extends TvSeason {
    @JsonProperty("show_id")
    private Integer showId;
}
