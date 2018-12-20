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
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("vote_count")
    private int voteCount;
    @JsonProperty("popularity")
    private Float popularity;


    private PersonType personType = PersonType.PERSON;

    public String getBackdropPath() { return backdropPath; }

    public Float getVoteAvg() { return voteAvg; }

    public String getFirstAirDate() { return firstAirDate; }

    public String getSeriesName() { return name; }

    public int getEpisodeCount() { return episodeCount; }

    public String getOverview() { return overview; }

    public String getLanguage() { return language; }

    public String getMediaType() { return mediaType; }

    public String getSeriesOriginalTitle() { return seriesOriginalTitle; }

    public String getCharacter() {
        return character;
    }


    public String getDepartment() {
        return department;
    }


    // convenience wrapper to make api more clear
    public int getMediaId() {
        return getId();
    }


    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }


    public String getMovieTitle() {
        return movieTitle;
    }


    public PersonType getPersonType() {
        return personType;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public float getPopularity() {
    	return popularity;
    }
    
    public int getVoteCount() {
        return voteCount;
    }

    public String getAdult() {
        return adult;
    }


    public void setLanguage( String language ) {
        this.language = language;
    }


    public void setEpisodeCount( int episodeCount ) {
        this.episodeCount = episodeCount;
    }


    public void setOverview( String overview ) {
        this.overview = overview;
    }


    public void setCharacter( String character ) {
        this.character = character;
    }


    public void setMovieOriginalTitle( String movieOriginalTitle ) {
        this.movieOriginalTitle = movieOriginalTitle;
    }


    public void setSeriesOriginalTitle( String seriesOriginalTitle ) {
        this.seriesOriginalTitle = seriesOriginalTitle;
    }


    public void setFirstAirDate( String firstAirDate ) {
        this.firstAirDate = firstAirDate;
    }


    public void setPosterPath( String posterPath ) {
        this.posterPath = posterPath;
    }


    public void setReleaseDate( String releaseDate ) {
        this.releaseDate = releaseDate;
    }


    public void setMovieTitle( String movieTitle ) {
        this.movieTitle = movieTitle;
    }


    public void setName( String name ) {
        this.name = name;
    }


    public void setDepartment( String department ) {
        this.department = department;
    }


    public void setAdult( String adult ) {
        this.adult = adult;
    }


    public void setMediaType( String mediaType ) {
        this.mediaType = mediaType;
    }


    public void setVoteAvg( Float voteAvg ) {
        this.voteAvg = voteAvg;
    }


    public void setBackdropPath( String backdropPath ) {
        this.backdropPath = backdropPath;
    }


    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }


    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }   
     
    
    public void setPersonType( PersonType personType ) {
        this.personType = personType;
    }
}
