package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("production_country")
public class ProductionCountry extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String isoCode;

    @JsonProperty("name")
    private String name;
}
