package info.movito.themoviedbapi.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @JsonProperty("include_adult")
    private Boolean includeAdult;

    @JsonProperty("username")
    private String username;
}
