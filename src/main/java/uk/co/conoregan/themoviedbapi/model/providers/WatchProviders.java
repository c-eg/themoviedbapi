package uk.co.conoregan.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WatchProviders extends AbstractJsonMapping {
    @JsonProperty("link")
    private String link;

    @JsonProperty("rent")
    private List<Provider> rentProviders;

    @JsonProperty("buy")
    private List<Provider> buyProviders;

    @JsonProperty("flatrate")
    private List<Provider> flatrateProviders;
}
