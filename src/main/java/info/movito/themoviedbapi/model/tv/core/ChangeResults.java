package info.movito.themoviedbapi.model.tv.core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeResults extends AbstractJsonMapping {
    @JsonProperty("changes")
    private List<Change> changedItems;
}
