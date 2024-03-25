package info.movito.themoviedbapi.model.find;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.tv.core.TvSeason;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindTvSeason extends TvSeason {
    @JsonProperty("show_id")
    private Integer showId;
}
