package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TvEpisode extends AbstractTvElement {


    @JsonProperty("overview")
    private String overview;


    @JsonProperty("air_date")
    private String airDate;


    @JsonProperty("episode_number")
    private int episodeNumber;

    @JsonProperty("still_path")
    private String stillPath;


    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;


    public String getOverview() {
        return overview;
    }


    public String getAirDate() {
        return airDate;
    }


    public int getEpisodeNumber() {
        return episodeNumber;
    }


    public String getStillPath() {
        return stillPath;
    }


    public float getVoteAverage() {
        return voteAverage;
    }


    public int getVoteCount() {
        return voteCount;
    }


    public void setOverview(String overview) {
        this.overview = overview;
    }


    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }


    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }


    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }


    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }


    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
