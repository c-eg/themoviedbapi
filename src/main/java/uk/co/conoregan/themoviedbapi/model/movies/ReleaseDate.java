package uk.co.conoregan.themoviedbapi.model.movies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseDate extends AbstractJsonMapping {
    @JsonProperty("certification")
    private String certification;

    @JsonProperty("descriptors")
    private List<Object> descriptors = new ArrayList<>();

    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("type")
    private ReleaseType type;
}
