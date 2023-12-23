package info.movito.themoviedbapi.model.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class GuestSession extends AbstractJsonMapping {
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("guest_session_id")
    private String guestSessionId;

    @JsonProperty("success")
    private Boolean success;
}
