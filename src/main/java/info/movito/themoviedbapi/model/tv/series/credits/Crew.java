package info.movito.themoviedbapi.model.tv.series.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.people.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Crew extends NamedIdElement {
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

    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;
}
