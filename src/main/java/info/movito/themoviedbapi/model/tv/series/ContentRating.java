package info.movito.themoviedbapi.model.tv.series;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContentRating extends AbstractJsonMapping {
    @JsonProperty("descriptors")
    private List<Object> descriptors = new ArrayList<>();

    @JsonProperty("iso_3166_1")
    private String iso31661;

    @JsonProperty("rating")
    private String rating;
}
