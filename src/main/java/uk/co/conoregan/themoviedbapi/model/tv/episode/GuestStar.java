package uk.co.conoregan.themoviedbapi.model.tv.episode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.people.Gender;

@Data
@EqualsAndHashCode(callSuper = true)
public class GuestStar extends NamedIdElement {
    @JsonProperty("character")
    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("order")
    private Integer order;

    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("profile_path")
    private String profilePath;
}
