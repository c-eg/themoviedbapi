package uk.co.conoregan.themoviedbapi.model.rated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.account.Movie;

@Data
@EqualsAndHashCode(callSuper = true)
public class RatedMovie extends Movie {
    @JsonProperty("rating")
    private Double rating;
}
