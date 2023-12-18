package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class Data extends AbstractJsonMapping {
    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("homepage")
    private String homepage;
}
