package info.movito.themoviedbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
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