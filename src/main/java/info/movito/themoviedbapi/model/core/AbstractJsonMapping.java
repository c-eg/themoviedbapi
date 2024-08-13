package info.movito.themoviedbapi.model.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * @author Holger Brandl
 */
public abstract class AbstractJsonMapping implements Serializable {
    @Serial
    private static final long serialVersionUID = -640106859893308644L;

    private final Map<String, Object> newItems = new HashMap<>();

    /**
     * Gets the new items that were not mapped to any field from a model extending this class.
     *
     * @return the new items.
     */
    @JsonAnyGetter
    public Map<String, Object> getNewItems() {
        return newItems;
    }

    @JsonAnySetter
    public void setNewItems(String name, Object value) {
        newItems.put(name, value);
    }
}
