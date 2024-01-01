package info.movito.themoviedbapi.model.keywords;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordMovie extends IdElement {
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
    private float voteAverage;

    @JsonProperty("vote_count")
    private double voteCount;

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("popularity")
    private float popularity;
}
