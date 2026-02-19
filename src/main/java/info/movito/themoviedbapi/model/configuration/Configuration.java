package info.movito.themoviedbapi.model.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Configuration extends AbstractJsonMapping {
    @JsonProperty("images")
    private ImageConfig imageConfig;

    @JsonProperty("change_keys")
    private List<String> changeKeys = new ArrayList<>();
}
