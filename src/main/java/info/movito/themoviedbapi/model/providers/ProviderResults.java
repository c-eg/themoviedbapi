package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.IdElement;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProviderResults extends IdElement {
    @JsonProperty("results")
    private Map<String, WatchProviders> results;

    public WatchProviders getProvidersForCountry(String country) {
        return results.getOrDefault(country, null);
    }
}
