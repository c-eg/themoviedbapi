package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Provider {
    @JsonProperty("display_priority")
    private Integer displayPriority;

    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("provider_id")
    private Integer providerId;

    @JsonProperty("provider_name")
    private String providerName;
}
