package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.collections.Part;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.NamedIdElement;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionInfo extends NamedIdElement {
    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("parts")
    private List<Part> parts;
}
