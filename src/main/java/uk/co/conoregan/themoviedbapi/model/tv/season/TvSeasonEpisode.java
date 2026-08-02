package uk.co.conoregan.themoviedbapi.model.tv.season;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.tv.core.credits.Crew;
import uk.co.conoregan.themoviedbapi.model.tv.episode.GuestStar;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvSeasonEpisode extends NamedIdElement {
    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("episode_number")
    private Integer episodeNumber;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("production_code")
    private String productionCode;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("still_path")
    private String stillPath;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("crew")
    private List<Crew> crew = new ArrayList<>();

    @JsonProperty("guest_stars")
    private List<GuestStar> guestStars = new ArrayList<>();

    @JsonProperty("show_id")
    private Integer showId;
}
