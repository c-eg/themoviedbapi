package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TokenAuthorisation {


    @JsonProperty("expires_at")
    private String expires;

    @JsonProperty("request_token")
    private String requestToken;

    @JsonProperty("success")
    private Boolean success;


    public String getExpires() {
        return expires;
    }

    public void setExpires( String expires ) {
        this.expires = expires;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken( String requestToken ) {
        this.requestToken = requestToken;
    }

    public Boolean getSuccess() {
        return success == null ? false : success;
    }

    public void setSuccess( Boolean success ) {
        this.success = success;
    }
}
