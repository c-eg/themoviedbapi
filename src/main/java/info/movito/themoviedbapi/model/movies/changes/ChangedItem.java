package info.movito.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangedItem extends IdElement {
    @JsonProperty("action")
    private String action;

    @JsonProperty("time")
    private String time;

    @JsonProperty("iso_639_1")
    private String language;

    @JsonProperty("value")
    private Object value;
}
