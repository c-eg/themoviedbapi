package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Holger Brandl
 */
public abstract class AbstractJsonMapping implements Serializable {
    private final Map<String, Object> newItems = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getNewItems() {
        return newItems;
    }

    @JsonAnySetter
    public void setNewItems(String name, Object value) {
        newItems.put(name, value);
    }
}
