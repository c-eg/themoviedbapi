package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Change extends IdElement {
    @JsonProperty("adult")
    private Boolean adult;
}
