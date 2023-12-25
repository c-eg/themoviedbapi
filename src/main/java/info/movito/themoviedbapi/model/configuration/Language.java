package info.movito.themoviedbapi.model.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Language extends AbstractJsonMapping {
    @JsonProperty("iso_639_1")
    private String isoCode;

    @JsonProperty("english_name")
    private String englishName;

    @JsonProperty("name")
    private String name;
}
