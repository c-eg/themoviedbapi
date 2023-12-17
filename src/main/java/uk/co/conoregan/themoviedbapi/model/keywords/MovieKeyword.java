package uk.co.conoregan.themoviedbapi.model.keywords;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieKeyword extends IdElement {
    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("title")
    private String title;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Double voteCount;

    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("popularity")
    private Double popularity;
}
