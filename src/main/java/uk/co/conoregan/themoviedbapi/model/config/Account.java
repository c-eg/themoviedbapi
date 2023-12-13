package uk.co.conoregan.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;

@EqualsAndHashCode(callSuper = true)
@Data
public class Account extends NamedIdElement {
    @JsonProperty("avatar")
    private Avatar avatar;

    // TODO: rename all iso_639_1 variable names to something consistent
    @JsonProperty("iso_639_1")
    private String iso6391;

    // TODO: rename all iso_3166_1 variable names to something consistent
    @JsonProperty("iso_3166_1")
    private String iso31661;

    @JsonProperty("name")
    private String name;

    @JsonProperty("include_adult")
    private boolean includeAdult;

    @JsonProperty("username")
    private String username;
}
