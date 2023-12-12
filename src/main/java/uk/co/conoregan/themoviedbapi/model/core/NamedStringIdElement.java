package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NamedStringIdElement extends StringIdElement {
    @JsonProperty("name")
    private String name;

    @JsonProperty("iso_639_1")
    private String iso639;
}
