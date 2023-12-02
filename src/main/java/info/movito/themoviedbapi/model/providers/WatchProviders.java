package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WatchProviders {
    @JsonProperty("link")
    private String link;

    @JsonProperty("rent")
    private List<Provider> rentProviders;

    @JsonProperty("buy")
    private List<Provider> buyProviders;

    @JsonProperty("flatrate")
    private List<Provider> flatrateProviders;

    public String getLink() {
        return link;
    }

    public WatchProviders setLink(String link) {
        this.link = link;
        return this;
    }

    public List<Provider> getRentProviders() {
        return rentProviders;
    }

    public WatchProviders setRentProviders(List<Provider> rentProviders) {
        this.rentProviders = rentProviders;
        return this;
    }

    public List<Provider> getBuyProviders() {
        return buyProviders;
    }

    public WatchProviders setBuyProviders(List<Provider> buyProviders) {
        this.buyProviders = buyProviders;
        return this;
    }

    public List<Provider> getFlatrateProviders() {
        return flatrateProviders;
    }

    public WatchProviders setFlatrateProviders(List<Provider> flatrateProviders) {
        this.flatrateProviders = flatrateProviders;
        return this;
    }
}
