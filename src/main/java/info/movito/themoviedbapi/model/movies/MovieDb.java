package info.movito.themoviedbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ProductionCountry;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.Language;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.providers.ProviderResults;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class MovieDb extends IdElement implements Multi {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("belongs_to_collection")
    private String belongsToCollection;

    @JsonProperty("budget")
    private Integer budget;

    @JsonProperty("genres")
    private List<Genre> genres;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies;

    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountries;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("revenue")
    private Integer revenue;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages;

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
    @JsonProperty("account_states")
    private AccountStates accountStates;

    @JsonProperty("alternative_titles")
    private AlternativeTitles alternativeTitles;

    @JsonProperty("credits")
    private Credits credits;

    @JsonProperty("changes")
    private ChangeResults changes;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @JsonProperty("images")
    private Images images;

    @JsonProperty("keywords")
    private KeywordResults keywords;

    @JsonProperty("recommendations")
    private MovieResultsPage recommendations;

    @JsonProperty("release_dates")
    private ReleaseDateResults releaseDates;

    @JsonProperty("lists")
    private MovieListResultsPage lists;

    @JsonProperty("reviews")
    private ReviewResultsPage reviews;

    @JsonProperty("similar")
    private MovieResultsPage similar;

    @JsonProperty("translations")
    private Translations translations;

    @JsonProperty("videos")
    private VideoResults videos;

    @JsonProperty("watch/providers")
    private ProviderResults watchProviders;

    public Optional<AccountStates> getAccountStates() {
        return Optional.ofNullable(accountStates);
    }

    public Optional<AlternativeTitles> getAlternativeTitles() {
        return Optional.ofNullable(alternativeTitles);
    }

    public Optional<Credits> getCredits() {
        return Optional.ofNullable(credits);
    }

    public Optional<ChangeResults> getChanges() {
        return Optional.ofNullable(changes);
    }

    public Optional<ExternalIds> getExternalIds() {
        return Optional.ofNullable(externalIds);
    }

    public Optional<Images> getImages() {
        return Optional.ofNullable(images);
    }

    public Optional<KeywordResults> getKeywords() {
        return Optional.ofNullable(keywords);
    }

    public Optional<MovieResultsPage> getRecommendations() {
        return Optional.ofNullable(recommendations);
    }

    public Optional<ReleaseDateResults> getReleaseDates() {
        return Optional.ofNullable(releaseDates);
    }

    public Optional<MovieListResultsPage> getLists() {
        return Optional.ofNullable(lists);
    }

    public Optional<ReviewResultsPage> getReviews() {
        return Optional.ofNullable(reviews);
    }

    public Optional<MovieResultsPage> getSimilar() {
        return Optional.ofNullable(similar);
    }

    public Optional<Translations> getTranslations() {
        return Optional.ofNullable(translations);
    }

    public Optional<VideoResults> getVideos() {
        return Optional.ofNullable(videos);
    }

    public Optional<ProviderResults> getWatchProviders() {
        return Optional.ofNullable(watchProviders);
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
