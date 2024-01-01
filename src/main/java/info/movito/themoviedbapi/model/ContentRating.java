package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("content_ratings")
public class ContentRating extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String locale;

    @JsonProperty("rating")
    private String rating;

    public static class Results extends IdElement {
        @JsonProperty("results")
        private List<ContentRating> results;

        public List<ContentRating> getContentRatings() {
            return results;
        }

        public void setContentRatings(List<ContentRating> contentRatings) {
            results = contentRatings;
        }
    }
}
