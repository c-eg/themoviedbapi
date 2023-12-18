package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigResults extends AbstractJsonMapping implements Serializable {
    @JsonProperty("images")
    private TmdbConfiguration tmdbConfiguration;

    @JsonProperty("change_keys")
    private List<String> changeKeys;
}
