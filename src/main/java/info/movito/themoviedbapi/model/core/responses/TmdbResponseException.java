package info.movito.themoviedbapi.model.core.responses;

import lombok.Getter;
import lombok.ToString;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.TmdbResponseCode;

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
}
