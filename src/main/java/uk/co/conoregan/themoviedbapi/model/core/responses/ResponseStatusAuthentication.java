package uk.co.conoregan.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseStatusAuthentication extends ResponseStatus {
    @JsonProperty("success")
    private boolean success;
}
