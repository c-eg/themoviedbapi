package uk.co.conoregan.themoviedbapi.model.watchproviders;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class Provider extends AbstractJsonMapping {
    @JsonProperty("display_priorities")
    private Map<String, Integer> displayPriorities = new HashMap<>();

    @JsonProperty("display_priority")
    private Integer displayPriority;

    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("provider_name")
    private String providerName;

    @JsonProperty("provider_id")
    private Integer providerId;
}
