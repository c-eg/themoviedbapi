package info.movito.themoviedbapi.model.core.watchproviders;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.core.annotation.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WatchProviders extends AbstractJsonMapping {
    @JsonProperty("link")
    private String link;

    @Optional
    @JsonProperty("rent")
    private List<Provider> rentProviders;

    @Optional
    @JsonProperty("buy")
    private List<Provider> buyProviders = new ArrayList<>();

    @Optional
    @JsonProperty("flatrate")
    private List<Provider> flatrateProviders = new ArrayList<>();

    @Optional
    @JsonProperty("free")
    private List<Provider> freeProviders = new ArrayList<>();

    @Optional
    @JsonProperty("ads")
    private List<Provider> adsProviders = new ArrayList<>();
}
