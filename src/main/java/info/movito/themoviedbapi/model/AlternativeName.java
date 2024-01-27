package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
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
