package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderResults extends AbstractJsonMapping {
    @JsonProperty("id")
    private int id;

    @JsonProperty("results")
    private Map<String, WatchProviders> results;

    public WatchProviders getProvidersForCountry(String country) {
        return results.getOrDefault(country, null);
    }
}
