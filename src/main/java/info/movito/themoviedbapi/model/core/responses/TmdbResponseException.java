package info.movito.themoviedbapi.model.core.responses;

import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TmdbResponseException extends TmdbException {
    private final TmdbResponseCode responseCode;

    public TmdbResponseException(TmdbResponseCode responseCode) {
        super(responseCode.toString());
        this.responseCode = responseCode;
    }

    public TmdbResponseException(String message) {
        super(message);
        this.responseCode = null;
    }

    public TmdbResponseException(Exception exception) {
        super(exception);
        this.responseCode = null;
    }
}
