package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
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

    public String getSeriesName() {
        return name;
    }

    // convenience wrapper to make api more clear
    public int getMediaId() {
        return getId();
    }
}
