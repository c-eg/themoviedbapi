package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TokenSession extends AbstractJsonMapping {
    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("status_message")
    private String statusMessage;

    @JsonProperty("guest_session_id")
    private String guestSessionId;

    @JsonProperty("expires_at")
    private String expiresAt;
}
