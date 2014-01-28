package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// todo fix or remove this
public class ChangesItems {

    @JsonProperty("changes")
    private List<ChangeKeyItem> changedItems = new ArrayList<ChangeKeyItem>();
    private final Map<String, Object> newItems = new HashMap<String, Object>();


    public List<ChangeKeyItem> getChangedItems() {
        return changedItems;
    }


    public void setChangedItems(List<ChangeKeyItem> changes) {
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


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
