package uk.co.conoregan.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvSeason extends AbstractTvElement {
    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("season_number")
    private int seasonNumber;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("episodes")
    private List<TvEpisode> episodes;
}
