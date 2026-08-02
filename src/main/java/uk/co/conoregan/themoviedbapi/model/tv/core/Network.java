package uk.co.conoregan.themoviedbapi.model.tv.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class Network extends NamedIdElement {
    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("origin_country")
    private String originCountry;
}
