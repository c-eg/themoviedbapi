package uk.co.conoregan.themoviedbapi.model.rated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.account.Movie;

@Getter
@Setter
public class RatedMovie extends Movie {
    @JsonProperty("rating")
    private Double rating;
}
