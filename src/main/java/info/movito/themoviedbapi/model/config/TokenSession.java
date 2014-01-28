package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TokenSession {


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


    public String getSessionId() {
        return sessionId;
    }


    public Boolean getSuccess() {
        return success;
    }


    public String getStatusCode() {
        return statusCode;
    }


    public String getStatusMessage() {
        return statusMessage;
    }


    public String getGuestSessionId() {
        return guestSessionId;
    }


    public String getExpiresAt() {
        return expiresAt;
    }
}
