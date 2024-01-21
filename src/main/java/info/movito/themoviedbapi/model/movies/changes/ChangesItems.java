package info.movito.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: fix or change this.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChangesItems extends AbstractJsonMapping {
    @JsonProperty("changes")
    private List<ChangeKeyItem> changedItems = new ArrayList<>();
}
