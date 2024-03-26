package info.movito.themoviedbapi.model.watchproviders;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Provider extends AbstractJsonMapping {
    @JsonProperty("display_priorities")
    private Map<String, Integer> displayPriorities;

    @JsonProperty("display_priority")
    private Integer displayPriority;

    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("provider_name")
    private String providerName;

    @JsonProperty("provider_id")
    private Integer providerId;
}
