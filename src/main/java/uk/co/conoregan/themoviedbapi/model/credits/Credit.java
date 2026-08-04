package uk.co.conoregan.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.StringIdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class Credit extends StringIdElement {
    @JsonProperty("credit_type")
    private CreditType creditType;

    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;

    @JsonProperty("media")
    private Media media;

    @JsonProperty("media_type")
    private MediaType mediaType;

    @JsonProperty("person")
    private Person person;
}
