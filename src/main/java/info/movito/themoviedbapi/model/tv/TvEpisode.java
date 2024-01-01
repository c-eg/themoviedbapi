package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvEpisode extends AbstractTvElement {
    @JsonProperty("overview")
    private String overview;

    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("show_id")
    private int seriesId;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("episode_number")
    private int episodeNumber;

    @JsonProperty("still_path")
    private String stillPath;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;
}
