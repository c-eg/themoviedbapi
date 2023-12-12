package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

import java.util.List;

@Getter
@Setter
public class Translations extends IdElement {
    @JsonProperty("translations")
    private List<Translation> translations;
}
