package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;

import java.util.HashMap;
import java.util.Map;


public class ChangedItem extends IdElement {

    @JsonProperty("action")
    private String action;
    @JsonProperty("time")
    private String time;
    @JsonProperty("iso_639_1")
    private String language;
    @JsonProperty("value")
    private Object value;
    private final Map<String, Object> newItems = new HashMap<String, Object>();


    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public Object getValue() {
        return value;
    }


    public void setValue(Object value) {
        this.value = value;
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
