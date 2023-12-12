package uk.co.conoregan.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Getter
@Setter
public class Change extends IdElement {
    @JsonProperty("adult")
    private boolean adult;
}
