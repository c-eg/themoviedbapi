package info.movito.themoviedbapi.model.movies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.Genre;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.Language;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ProductionCompany;
import info.movito.themoviedbapi.model.core.ProductionCountry;
import info.movito.themoviedbapi.model.core.ReviewResultsPage;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class MovieDb extends IdElement {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("belongs_to_collection")
    private BelongsToCollection belongsToCollection;

    @JsonProperty("budget")
    private Integer budget;

    @JsonProperty("genres")
    private List<Genre> genres = new ArrayList<>();

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("origin_country")
    private List<String> originCountry = new ArrayList<>();

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies = new ArrayList<>();

    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountries = new ArrayList<>();

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("revenue")
    private Long revenue;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages = new ArrayList<>();

    @JsonProperty("status")
    private String status;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("title")
    private String title;

    @JsonProperty("video")
    private Boolean video;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    /* append to responses */

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("account_states")
    private AccountStates accountStates;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("alternative_titles")
    private AlternativeTitles alternativeTitles;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("credits")
    private Credits credits;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("changes")
    private ChangeResults changes;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("images")
    private Images images;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("keywords")
    private KeywordResults keywords;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("recommendations")
    private MovieResultsPage recommendations;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("release_dates")
    private ReleaseDateResults releaseDates;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("lists")
    private MovieListResultsPage lists;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("reviews")
    private ReviewResultsPage reviews;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("similar")
    private MovieResultsPage similar;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("translations")
    private Translations translations;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("videos")
    private VideoResults videos;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("watch/providers")
    private ProviderResults watchProviders;
}
