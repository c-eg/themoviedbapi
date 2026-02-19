package info.movito.themoviedbapi.model.core.watchproviders;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WatchProviders extends AbstractJsonMapping {
    @JsonProperty("link")
    private String link;

    @JsonProperty("rent")
    private List<Provider> rentProviders;

    @JsonProperty("buy")
    private List<Provider> buyProviders = new ArrayList<>();

    @JsonProperty("flatrate")
    private List<Provider> flatrateProviders = new ArrayList<>();
}
