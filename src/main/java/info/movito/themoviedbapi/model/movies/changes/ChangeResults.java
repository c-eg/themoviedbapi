package info.movito.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeResults extends AbstractJsonMapping {
    @JsonProperty("changes")
    private List<Change> changedItems;
}
