package uk.co.conoregan.themoviedbapi.model.core.watchproviders;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProviderResults extends IdElement {
    @JsonProperty("results")
    private Map<String, WatchProviders> results = new HashMap<>();
}
