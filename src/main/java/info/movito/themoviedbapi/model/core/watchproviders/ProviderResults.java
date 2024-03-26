package info.movito.themoviedbapi.model.core.watchproviders;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProviderResults extends IdElement {
    @JsonProperty("results")
    private Map<String, WatchProviders> results;
}
