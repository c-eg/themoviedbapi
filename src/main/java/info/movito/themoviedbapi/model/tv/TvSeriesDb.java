package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.model.ContentRating;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.people.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.Multi;

import java.util.List;

/**
 * @author Holger Brandl
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class TvSeriesDb extends AbstractTvElement implements Multi {
    @JsonProperty("created_by")
    private List<Person> createdBy;

    @JsonProperty("episode_run_time")
    private List<Integer> episodeRuntime;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("last_air_date")
    private String lastAirDate;

    @JsonProperty("genres")
    private List<Genre> genres;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("origin_country")
    private List<String> originCountry;

    @JsonProperty("networks")
    private List<Network> networks;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("number_of_episodes")
    private Integer numberOfEpisodes;

    @JsonProperty("number_of_seasons")
    private Integer numberOfSeasons;

    @JsonProperty("seasons")
    private List<TvSeason> seasons;

    @JsonProperty("recommendations")
    private ResultsPage<TvSeriesDb> recommendations;

    @JsonProperty("rating")
    private Double userRating;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("content_ratings")
    private ContentRating.Results contentRatings;

    @Override
    public MediaType getMediaType() {
        return MediaType.TV_SERIES;
    }
}
