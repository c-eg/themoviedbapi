package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("content_ratings")
public class ContentRating extends AbstractJsonMapping implements Serializable {
    @JsonProperty("iso_3166_1")
    private String locale;

    @JsonProperty("rating")
    private String rating;

    // TODO: move into it's own file
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Results extends IdElement {
        @JsonProperty("results")
        private List<ContentRating> results;
    }
}
