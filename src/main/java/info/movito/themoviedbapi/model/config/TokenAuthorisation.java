package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TokenAuthorisation {
    @JsonProperty("expires_at")
    private String expires;

    @JsonProperty("request_token")
    private String requestToken;

    @JsonProperty("success")
    private Boolean success;
}
