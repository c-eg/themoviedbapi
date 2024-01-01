package info.movito.themoviedbapi.model.core;

import info.movito.themoviedbapi.tools.MovieDbException;
import lombok.Getter;

@Getter
public class ResponseStatusException extends MovieDbException {
    private final ResponseStatus responseStatus;

    /**
     * Constructor.
     */
    public ResponseStatusException(ResponseStatus responseStatus) {
        super(responseStatus.getStatusCode() + " :: " + responseStatus.getStatusMessage());

        this.responseStatus = responseStatus;
    }

    @Override
    public String toString() {
        return responseStatus.toString();
    }
}
