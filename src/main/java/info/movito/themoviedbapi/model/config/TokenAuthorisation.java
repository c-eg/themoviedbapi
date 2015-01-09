package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TokenAuthorisation {


    @JsonProperty("expires_at")
    private String expires;

    @JsonProperty("request_token")
    private String requestToken;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("status_message")
    private String status_message;

    @JsonProperty("status_code")
    private int status_code;


    public String getExpires() {
        return expires;
    }


    public String getRequestToken() {
        return requestToken;
    }

    public String getStatusMessage() {
    	return status_message;
    }

    public int getStatusCode() {
    	return status_code;
    }
    
    public Boolean getSuccess() {
        return success == null ? false : success;
    }
}
