package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResultDates extends AbstractJsonMapping {
    @JsonProperty("minimum")
    private String minimum;

    @JsonProperty("maximum")
    private String maximum;
}
