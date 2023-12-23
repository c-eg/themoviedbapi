package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlternativeTitle extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String country;

    @JsonProperty("title")
    private String title;
}
