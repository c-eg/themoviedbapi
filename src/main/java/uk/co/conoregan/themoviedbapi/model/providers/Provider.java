package uk.co.conoregan.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = true)
public class Provider extends AbstractJsonMapping {
    @JsonProperty("display_priority")
    private Integer displayPriority;

    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("provider_id")
    private Integer providerId;

    @JsonProperty("provider_name")
    private String providerName;
}
