package info.movito.themoviedbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.people.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cast extends IdElement {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("name")
    private String name;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("profile_path")
    private String profilePath;

    @JsonProperty("cast_id")
    private Integer castId;

    @JsonProperty("character")
    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("order")
    private Integer order;
}
