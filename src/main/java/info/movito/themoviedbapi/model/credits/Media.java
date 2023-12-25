package info.movito.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Media extends NamedIdElement {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_name")
    private String originalTitle;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("origin_country")
    private List<String> originCountry;

    @JsonProperty("character")
    private String character;

    @JsonProperty("episodes")
    private List<?> episodes;

    @JsonProperty("seasons")
    private List<Season> seasons;
}
