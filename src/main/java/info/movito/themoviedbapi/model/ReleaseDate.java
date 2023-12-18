package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReleaseDate extends AbstractJsonMapping {
    @JsonProperty("iso_639_1")
    private String language;

    @JsonProperty("certification")
    private String certification;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("type")
    private String type;
}
