package info.movito.themoviedbapi.model.core.watchproviders;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
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
