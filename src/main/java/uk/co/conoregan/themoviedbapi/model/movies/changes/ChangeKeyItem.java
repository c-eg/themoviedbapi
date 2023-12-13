package uk.co.conoregan.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeKeyItem extends AbstractJsonMapping {
    @JsonProperty("key")
    private String key;

    @JsonProperty("items")
    private List<ChangedItem> changedItems;
}
