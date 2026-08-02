package uk.co.conoregan.themoviedbapi.model.tv.series;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.people.Gender;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreatedBy extends NamedIdElement {
    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("profile_path")
    private String profilePath;
}
