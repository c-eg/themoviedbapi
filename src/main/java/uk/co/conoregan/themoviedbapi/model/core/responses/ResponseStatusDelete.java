package uk.co.conoregan.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseStatusDelete extends AbstractJsonMapping {
    @JsonProperty("success")
    private boolean success;
}
