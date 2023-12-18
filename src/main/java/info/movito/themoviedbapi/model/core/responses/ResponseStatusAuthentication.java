package info.movito.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseStatusAuthentication extends ResponseStatus {
    @JsonProperty("success")
    private Boolean success;
}
