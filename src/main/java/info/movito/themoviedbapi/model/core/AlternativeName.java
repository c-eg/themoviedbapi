package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlternativeName extends AbstractJsonMapping {
    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;
}
