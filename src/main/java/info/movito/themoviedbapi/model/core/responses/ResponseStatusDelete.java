package info.movito.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseStatusDelete extends AbstractJsonMapping {
    @JsonProperty("success")
    private Boolean success;
}
