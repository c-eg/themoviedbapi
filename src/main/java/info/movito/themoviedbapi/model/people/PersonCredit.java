package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;


public class PersonCredit extends IdElement {

    // note:  id here refers to a movie not a cast or crew

    @JsonProperty("character")
    private String character;
    @JsonProperty("original_title")
    private String movieOriginalTitle;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("title")
    private String movieTitle;
    @JsonProperty("department")
    private String department;
    @JsonProperty("job")
    private String job;
    @JsonProperty("adult")
    private String adult;

    private PersonType personType = PersonType.PERSON;


    public String getCharacter() {
        return character;
    }


    public String getDepartment() {
        return department;
    }


    public String getJob() {
        return job;
    }


    // convenience wrapper to make api more clear
    public int getMovieId() {
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
}
