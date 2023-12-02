package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;

public class PersonCredit extends IdElement {
    // note:  id here refers to a movie not a cast or crew
    @JsonProperty("original_language")
    private String language;

    @JsonProperty("episode_count")
    private int episodeCount;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("character")
    private String character;

    @JsonProperty("original_title")
    private String movieOriginalTitle;

    @JsonProperty("original_name")
    private String seriesOriginalTitle;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("title")
    private String movieTitle;

    @JsonProperty("name")
    private String name;

    @JsonProperty("department")
    private String department;

    @JsonProperty("adult")
    private String adult;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("vote_average")
    private Float voteAvg;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("popularity")
    private Float popularity;

    private PersonType personType = PersonType.PERSON;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Float getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(Float voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getSeriesName() {
        return name;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getSeriesOriginalTitle() {
        return seriesOriginalTitle;
    }

    public void setSeriesOriginalTitle(String seriesOriginalTitle) {
        this.seriesOriginalTitle = seriesOriginalTitle;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // convenience wrapper to make api more clear
    public int getMediaId() {
        return getId();
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        this.movieOriginalTitle = movieOriginalTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setName(String name) {
        this.name = name;
    }
}
