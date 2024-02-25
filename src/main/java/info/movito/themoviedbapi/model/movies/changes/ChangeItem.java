package info.movito.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.StringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeItem extends StringIdElement {
    @JsonProperty("action")
    private String action;

    @JsonProperty("time")
    private String time;

    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("iso_3166_1")
    private String iso31661;

    @JsonProperty("value")
    private Object value;
}
