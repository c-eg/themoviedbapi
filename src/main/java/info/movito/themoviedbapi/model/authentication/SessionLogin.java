package info.movito.themoviedbapi.model.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = true)
public class SessionLogin extends AbstractJsonMapping {
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("request_token")
    private String requestToken;

    @JsonProperty("success")
    private Boolean success;
}
