package uk.co.conoregan.themoviedbapi.model.tv.series;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScreenedTheatrically extends IdElement {
    @JsonProperty("episode_number")
    private Integer episodeNumber;

    @JsonProperty("season_number")
    private Integer seasonNumber;
}
