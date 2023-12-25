package info.movito.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Season extends NamedIdElement {
    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("episode_count")
    private Integer episodeCount;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("show_id")
    private Integer showId;
}
