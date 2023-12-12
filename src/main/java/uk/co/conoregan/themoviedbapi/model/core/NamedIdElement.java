package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NamedIdElement extends IdElement {
    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return getName() + " [" + getId() + "]";
    }
}
