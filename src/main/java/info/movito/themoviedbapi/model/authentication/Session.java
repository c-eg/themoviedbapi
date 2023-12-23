package info.movito.themoviedbapi.model.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class Session extends AbstractJsonMapping {
    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("success")
    private Boolean success;
}
