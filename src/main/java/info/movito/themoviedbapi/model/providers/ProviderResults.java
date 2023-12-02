package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ProviderResults {
    @JsonProperty("id")
    private int id;

    @JsonProperty("results")
    private Map<String, WatchProviders> results;

    public Map<String, WatchProviders> getResults() {
        return results;
    }

    public ProviderResults setResults(Map<String, WatchProviders> results) {
        this.results = results;
        return this;
    }

    public WatchProviders getProvidersForCountry(String country) {
        return results.getOrDefault(country, null);
    }
}
