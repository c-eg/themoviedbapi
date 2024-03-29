package info.movito.themoviedbapi.model.tv.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"media_type"})
public class TvEpisode extends NamedIdElement {
    @JsonProperty("overview")
    private String overview;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("episode_number")
    private Integer episodeNumber;

    @JsonProperty("production_code")
    private String productionCode;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("show_id")
    private Integer showId;

    @JsonProperty("still_path")
    private String stillPath;
}
