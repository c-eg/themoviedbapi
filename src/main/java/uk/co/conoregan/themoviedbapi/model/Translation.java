package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Translation {
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
