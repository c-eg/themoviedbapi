package uk.co.conoregan.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseStatusDelete {
    @JsonProperty("success")
    private boolean success;

    @Override
    public String toString() {
        return "success: " + success;
    }
}
