package info.movito.themoviedbapi.model.certifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class Certification extends AbstractJsonMapping {
    @JsonProperty("certification")
    private String certification;

    @JsonProperty("meaning")
    private String meaning;

    @JsonProperty("order")
    private Integer order;
}
