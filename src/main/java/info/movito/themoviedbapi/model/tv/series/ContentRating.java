package info.movito.themoviedbapi.model.tv.series;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContentRating extends AbstractJsonMapping {
    @JsonProperty("descriptors")
    private List<Object> descriptors;

    @JsonProperty("iso_3166_1")
    private String locale;

    @JsonProperty("rating")
    private String rating;
}
