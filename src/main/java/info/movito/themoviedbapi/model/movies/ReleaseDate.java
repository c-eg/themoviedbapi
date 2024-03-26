package info.movito.themoviedbapi.model.movies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseDate extends AbstractJsonMapping {
    @JsonProperty("certification")
    private String certification;

    @JsonProperty("descriptors")
    private List<Object> descriptors;

    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("type")
    private ReleaseType type;
}
