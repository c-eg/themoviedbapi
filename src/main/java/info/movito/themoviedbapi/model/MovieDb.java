package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.MovieKeywords;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import info.movito.themoviedbapi.model.providers.ProviderResults;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class MovieDb extends IdElement implements Multi {
    @JsonProperty("title")
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("popularity")
    private float popularity;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("belongs_to_collection")
    private Collection belongsToCollection;

    @JsonProperty("budget")
    private long budget;

    @JsonProperty("genres")
    private List<Genre> genres;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("overview")
    private String overview;

    // todo still there??
    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanies;

    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountries;

    @JsonProperty("revenue")
    private long revenue;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("status")
    private String status;

    // Appendable responses
    @JsonProperty("alternative_titles")
    private MoviesAlternativeTitles alternativeTitles;

    @JsonProperty("credits")
    private Credits credits;

    @JsonProperty("images")
    private MovieImages images;

    // note: it seems to be a flaw in their api, because a paged result would be more consistent
    @JsonProperty("keywords")
    private MovieKeywords keywords;

    @JsonProperty("release_dates")
    private TmdbMovies.ReleaseInfoResults releases;

    @JsonProperty("videos")
    private Video.Results videos;

    @JsonProperty("translations")
    private Translations translations;

    @JsonProperty("similar")
    private ResultsPage<MovieDb> similarMovies;

    @JsonProperty("recommendations")
    private ResultsPage<MovieDb> recommendedMovies;

    @JsonProperty("reviews")
    private ResultsPage<Review> reviews;

    @JsonProperty("lists")
    private ResultsPage<MovieList> lists;

    @JsonProperty("watch/providers")
    private ProviderResults watchProviders;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    public List<AlternativeTitle> getAlternativeTitles() {
        return alternativeTitles != null ? alternativeTitles.getTitles() : null;
    }

    public List<PersonCast> getCast() {
        return credits != null ? credits.getCast() : null;
    }

    public List<PersonCrew> getCrew() {
        return credits != null ? credits.getCrew() : null;
    }

    public List<Artwork> getImages(ArtworkType... artworkTypes) {
        return images != null ? images.getAll(artworkTypes) : null;
    }

    public List<Keyword> getKeywords() {
        return keywords != null ? keywords.getKeywords() : null;
    }

    public List<ReleaseInfo> getReleases() {
        return releases != null ? releases.getResults() : null;
    }

    public List<Video> getVideos() {
        return videos != null ? videos.getVideos() : null;
    }

    public List<Translation> getTranslations() {
        return translations != null ? translations.getTranslations() : null;
    }

    public List<MovieDb> getSimilarMovies() {
        return similarMovies != null ? similarMovies.getResults() : null;
    }

    public List<MovieDb> getRecommendations() {
        return recommendedMovies != null ? recommendedMovies.getResults() : null;
    }

    public List<MovieList> getLists() {
        return lists != null ? lists.getResults() : null;
    }

    public List<Review> getReviews() {
        return reviews != null ? reviews.getResults() : null;
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
