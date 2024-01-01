package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeKeyItem {
    private final Map<String, Object> newItems = new HashMap<>();

    @JsonProperty("key")
    private String key;

    @JsonProperty("items")
    private List<ChangedItem> changedItems = new ArrayList<>();

    @JsonAnyGetter
    public Map<String, Object> getNewItems() {
        return this.newItems;
    }

    @JsonAnySetter
    public void setNewItems(String name, Object value) {
        this.newItems.put(name, value);
    }
}
