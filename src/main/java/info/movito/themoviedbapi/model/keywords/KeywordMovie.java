package info.movito.themoviedbapi.model.keywords;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;


public class KeywordMovie extends IdElement {


    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("title")
    private String title;
    @JsonProperty("vote_average")
    private float voteAverage;
    @JsonProperty("vote_count")
    private double voteCount;
    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("popularity")
    private float popularity;


    public String getBackdropPath() {
        return backdropPath;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }


    public String getReleaseDate() {
        return releaseDate;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public String getTitle() {
        return title;
    }


    public float getVoteAverage() {
        return voteAverage;
    }


    public double getVoteCount() {
        return voteCount;
    }


    public boolean isAdult() {
        return adult;
    }


    public float getPopularity() {
        return popularity;
    }


    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }


    public void setVoteCount(double voteCount) {
        this.voteCount = voteCount;
    }


    public void setAdult(boolean adult) {
        this.adult = adult;
    }


    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

}
