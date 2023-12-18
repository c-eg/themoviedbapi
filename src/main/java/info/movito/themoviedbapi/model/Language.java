package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("spoken_language")
public class Language extends AbstractJsonMapping {
    @JsonProperty("iso_639_1")
    private String isoCode;

    @JsonProperty("name")
    private String name;
}
