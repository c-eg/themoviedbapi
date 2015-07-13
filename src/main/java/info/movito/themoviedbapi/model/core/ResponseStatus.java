package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;


/**
 * See https://www.themoviedb.org/documentation/api/status-codes
 */
public class ResponseStatus extends AbstractJsonMapping {


    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("status_message")
    private String statusMessage;


    public ResponseStatus() {
    }


    public ResponseStatus(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }


    public Integer getStatusCode() {
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


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("code", getStatusCode()).add("message", getStatusMessage()).toString();
    }
}
