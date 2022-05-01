package info.movito.themoviedbapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class WatchProviders extends AbstractJsonMapping {

    // @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    // static class BuyProvider extends AbstractJsonMapping {

    // }

    // @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    // static class RentProvider extends AbstractJsonMapping {

    // }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public static class Provider extends AbstractJsonMapping {
        private String providerName;
        private Long providerId;
        private Long displayPriority;

        @JsonProperty("provider_name")
        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }

        @JsonProperty("provider_id")
        public Long getProviderId() {
            return providerId;
        }

        public void setProviderId(Long providerId) {
            this.providerId = providerId;
        }

        @JsonProperty("display_priority")
        public Long getDisplayPriority() {
            return displayPriority;
        }

        public void setDisplayPriority(Long displayPriority) {
            this.displayPriority = displayPriority;
        }

        @JsonProperty("logo_path")
        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        private String logoPath;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public static class Results extends AbstractJsonMapping {

        @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
        public static class US {

            private String link;
            private List<Provider> flatrateProviders;
            private List<Provider> buyProviders;
            private List<Provider> rentProviders;

            @JsonProperty("flatrate")
            public List<Provider> getFlatrateProviders() {
                return flatrateProviders;
            }
    
            public void setFlatrateProviders(List<Provider> flatrateProviders) {
                this.flatrateProviders = flatrateProviders;
            }

            @JsonProperty("buy")
            public List<Provider> getBuyProviders() {
                return buyProviders;
            }

            public void setBuyProviders(List<Provider> buyProviders) {
                this.buyProviders = buyProviders;
            }

            @JsonProperty("rent")
            public List<Provider> getRentProviders() {
                return rentProviders;
            }

            public void setRentProviders(List<Provider> rentProviders) {
                this.rentProviders = rentProviders;
            }

            @JsonProperty("link")
            public String getLink() {
                return this.link;
            }

            public void setLink(final String link) {
                this.link = link;
            }
        }

        private US US;

        @JsonProperty("US")
        public US getUS() {
            return this.US;
        }

        public void setUS(final US us) {
            this.US = us;
        }
    }

    private Results results;

    @JsonProperty("results")
    public Results getResults() {
        return this.results;
    }

    public void setResults(final Results results) {
        this.results = results;
    }
}
