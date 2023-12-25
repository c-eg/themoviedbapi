package info.movito.themoviedbapi.model.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Country extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String iso31661;

    @JsonProperty("english_name")
    private String englishName;

    @JsonProperty("native_name")
    private String nativeName;
}
