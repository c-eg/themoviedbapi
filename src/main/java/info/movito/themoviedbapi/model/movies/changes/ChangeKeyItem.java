package info.movito.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeKeyItem extends AbstractJsonMapping {
    @JsonProperty("key")
    private String key;

    @JsonProperty("items")
    private List<ChangedItem> changedItems = new ArrayList<>();
}
