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
}
