package info.movito.themoviedbapi.model.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ImageConfig extends AbstractJsonMapping {
    @JsonProperty("base_url")
    private String baseUrl;

    @JsonProperty("secure_base_url")
    private String secureBaseUrl;

    @JsonProperty("poster_sizes")
    private List<String> posterSizes = new ArrayList<>();

    @JsonProperty("backdrop_sizes")
    private List<String> backdropSizes = new ArrayList<>();

    @JsonProperty("profile_sizes")
    private List<String> profileSizes = new ArrayList<>();

    @JsonProperty("logo_sizes")
    private List<String> logoSizes = new ArrayList<>();

    @JsonProperty("still_sizes")
    private List<String> stillSizes = new ArrayList<>();
}
