package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigResults extends AbstractJsonMapping implements Serializable {
    @JsonProperty("images")
    private TmdbConfiguration tmdbConfiguration;

    @JsonProperty("change_keys")
    private List<String> changeKeys;
}
