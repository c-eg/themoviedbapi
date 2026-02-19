package info.movito.themoviedbapi.model.find;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FindResults extends AbstractJsonMapping {
    @JsonProperty("movie_results")
    private List<FindMovie> movieResults = new ArrayList<>();

    @JsonProperty("person_results")
    private List<FindPerson> personResults = new ArrayList<>();

    @JsonProperty("tv_results")
    private List<FindTvSeries> tvSeriesResults = new ArrayList<>();

    @JsonProperty("tv_season_results")
    private List<FindTvSeason> tvSeasonResults = new ArrayList<>();

    @JsonProperty("tv_episode_results")
    private List<FindTvEpisode> tvEpisodeResults = new ArrayList<>();
}
