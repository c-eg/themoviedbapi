package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChangeKeyItem {

    @JsonProperty("key")
    private String key;
    @JsonProperty("items")
    private List<ChangedItem> changedItems = new ArrayList<ChangedItem>();

    private Map<String, Object> newItems = new HashMap<String, Object>();


    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public List<ChangedItem> getChangedItems() {
        return changedItems;
    }


    public void setChangedItems(List<ChangedItem> changes) {
        this.changedItems = changes;
    }


    @JsonAnyGetter
    public Map<String, Object> getNewItems() {
        return this.newItems;
    }


    @JsonAnySetter
    public void setNewItems(String name, Object value) {
        this.newItems.put(name, value);
    }

}
