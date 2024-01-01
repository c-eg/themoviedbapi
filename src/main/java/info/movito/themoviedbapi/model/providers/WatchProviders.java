package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class WatchProviders {
    @JsonProperty("link")
    private String link;

    @JsonProperty("rent")
    private List<Provider> rentProviders;

    @JsonProperty("buy")
    private List<Provider> buyProviders;

    @JsonProperty("flatrate")
    private List<Provider> flatrateProviders;
}
