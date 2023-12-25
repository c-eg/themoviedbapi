package info.movito.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.StringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaCredit extends StringIdElement {
    @JsonProperty("credit_type")
    private String creditType;

    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;

    @JsonProperty("media")
    private Media media;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("person")
    private PersonCredit personCredit;
}
