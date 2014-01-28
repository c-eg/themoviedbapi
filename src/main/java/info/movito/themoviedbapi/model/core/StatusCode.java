package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StatusCode extends AbstractJsonMapping {


    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("status_message")
    private String statusMessage;


    public int getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public String getStatusMessage() {
        return statusMessage;
    }


    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
