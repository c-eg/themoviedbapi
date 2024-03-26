package info.movito.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.tools.TmdbResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseStatus extends AbstractJsonMapping {
    @JsonProperty("status_code")
    private TmdbResponseCode statusCode;

    @JsonProperty("status_message")
    private String statusMessage;
}
