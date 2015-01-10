package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.MovieDbExceptionType;


public class StatusCode extends AbstractJsonMapping {


    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("status_message")
    private String statusMessage;


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

    public void process() {
        // if null, the json response was not a error status code, and but something else
        // if code is 1, the request was successful
        if (getStatusCode() == null || getStatusCode() == 1) {
            return;
        }

        switch (getStatusCode()) {
            case 6:
                throw new MovieDbException(MovieDbExceptionType.INVALID_ID, getStatusMessage(), getStatusCode());
            case 30:
                throw new MovieDbException(MovieDbExceptionType.AUTHORISATION_FAILURE, getStatusMessage(), getStatusCode());
            default:
                // do nothing
//                throw new MovieDbException(MovieDbExceptionType.UNKNOWN_CAUSE, getStatusMessage());

        }
    }
}
