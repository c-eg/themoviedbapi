package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResultDates extends AbstractJsonMapping {
    @JsonProperty("minimum")
    private String minimum;

    @JsonProperty("maximum")
    private String maximum;
}
