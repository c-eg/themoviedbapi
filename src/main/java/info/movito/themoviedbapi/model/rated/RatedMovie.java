package info.movito.themoviedbapi.model.rated;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.account.Movie;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RatedMovie extends Movie {
    @JsonProperty("rating")
    private Double rating;
}
