package uk.co.conoregan.themoviedbapi.model.core.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Getter
@Setter
@ToString
public class ResponseStatus extends AbstractJsonMapping {
    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("status_message")
    private String statusMessage;

    public ResponseStatus() {
    }
}
