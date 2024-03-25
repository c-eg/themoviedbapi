package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.find.FindMovie;
import info.movito.themoviedbapi.model.find.FindPerson;
import info.movito.themoviedbapi.model.find.FindTvEpisode;
import info.movito.themoviedbapi.model.find.FindTvSeason;
import info.movito.themoviedbapi.model.find.FindTvSeries;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FindResults extends AbstractJsonMapping {
    @JsonProperty("movie_results")
    private List<FindMovie> movieResults;

    @JsonProperty("person_results")
    private List<FindPerson> personResults;

    @JsonProperty("tv_results")
    private List<FindTvSeries> tvSeriesResults;

    @JsonProperty("tv_season_results")
    private List<FindTvSeason> tvSeasonResults;

    @JsonProperty("tv_episode_results")
    private List<FindTvEpisode> tvEpisodeResults;
}
