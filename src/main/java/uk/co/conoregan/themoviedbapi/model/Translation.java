package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class Translation extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String countryCode;

    @JsonProperty("iso_639_1")
    private String languageCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("english_name")
    private String englishName;

    @JsonProperty("data")
    private Data data;
}
