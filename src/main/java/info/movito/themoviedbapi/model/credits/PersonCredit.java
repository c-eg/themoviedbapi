package info.movito.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCredit extends NamedIdElement {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("profile_path")
    private String profilePath;
}
