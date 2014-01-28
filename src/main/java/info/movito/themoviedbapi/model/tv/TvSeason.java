package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


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


    public String getAirDate() {
        return airDate;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public int getSeasonNumber() {
        return seasonNumber;
    }


    public void setEpisodes(List<TvEpisode> episodes) {
        this.episodes = episodes;
    }


    public List<TvEpisode> getEpisodes() {


        return episodes;
    }


    public String getOverview() {
        return overview;
    }
}
