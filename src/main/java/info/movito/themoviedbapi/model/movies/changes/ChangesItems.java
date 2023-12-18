package info.movito.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

/**
 * TODO: fix or change this.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangesItems extends AbstractJsonMapping {
    @JsonProperty("changes")
    private List<ChangeKeyItem> changedItems;
}
