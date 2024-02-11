package info.movito.themoviedbapi.model.peoplelists;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PopularPerson extends NamedIdElement {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("known_for")
    private List<KnownFor> knownFor;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("profile_path")
    private String profilePath;
}
