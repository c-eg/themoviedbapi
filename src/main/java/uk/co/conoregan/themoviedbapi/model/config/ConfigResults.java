package uk.co.conoregan.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ConfigResults extends AbstractJsonMapping implements Serializable {
    @JsonProperty("images")
    private TmdbConfiguration tmdbConfiguration;

    @JsonProperty("change_keys")
    private List<String> changeKeys;
}
