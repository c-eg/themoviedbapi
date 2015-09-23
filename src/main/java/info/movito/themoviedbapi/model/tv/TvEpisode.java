package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;


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


    public String getOverview() {
        return overview;
    }


    public String getAirDate() {
        return airDate;
    }


    /**
     * Just set when fetching rated tv-episodes.
     */
    public Integer getSeriesId() {
        return seriesId;
    }


    public int getSeasonNumber() {
        return seasonNumber;
    }


    public int getEpisodeNumber() {
        return episodeNumber;
    }


    public String getStillPath() {
        return stillPath;
    }


    public float getUserRating() {
        return userRating;
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
