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


    public String getAdult() {
        return adult;
    }
    
    public float getPopularity() {
    	return popularity;
    }
    
    public int getVoteCount() {
    	return voteCount;
    }
}
