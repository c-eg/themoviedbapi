package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.movies.ReleaseType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseDate extends AbstractJsonMapping {
    @JsonProperty("certification")
    private String certification;

    @JsonProperty("descriptors")
    private List<String> descriptors;

    @JsonProperty("iso_639_1")
    private String language;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("type")
    private ReleaseType type;
}
