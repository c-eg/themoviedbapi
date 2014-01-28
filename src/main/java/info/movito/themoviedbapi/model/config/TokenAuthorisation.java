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


    public String getRequestToken() {
        return requestToken;
    }


    public Boolean getSuccess() {
        return success;
    }
}
