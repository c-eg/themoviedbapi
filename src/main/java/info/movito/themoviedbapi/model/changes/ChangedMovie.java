package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangedMovie extends IdElement {
    @JsonProperty("adult")
    private boolean adult;
}
