package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.MovieKeywords;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.movie.MovieTrailers;
import info.movito.themoviedbapi.model.movie.MovieTranslations;
import info.movito.themoviedbapi.model.movie.MoviesAlternativeTitles;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;

import java.util.List;


public class MovieDb extends IdElement {

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

    @JsonProperty("releases")
    private TmdbMovies.ReleaseInfoResults releases;

    @JsonProperty("trailers")
    private MovieTrailers trailers;

    @JsonProperty("translations")
    private MovieTranslations translations;

    @JsonProperty("similar_movies")
    private ResultsPage<MovieDb> similarMovies;

    @JsonProperty("reviews")
    private ResultsPage<Reviews> reviews;

    @JsonProperty("lists")
    private ResultsPage<MovieList> lists;


    public String getBackdropPath() {
        return backdropPath;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }


    public float getPopularity() {
        return popularity;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public String getReleaseDate() {
        return releaseDate;
    }


    public String getTitle() {
        return title;
    }


    public boolean isAdult() {
        return adult;
    }


    public Collection getBelongsToCollection() {
        return belongsToCollection;
    }


    public long getBudget() {
        return budget;
    }


    public List<Genre> getGenres() {
        return genres;
    }


    public String getHomepage() {
        return homepage;
    }


    public String getImdbID() {
        return imdbID;
    }


    public String getOverview() {
        return overview;
    }


    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }


    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }


    public long getRevenue() {
        return revenue;
    }


    public int getRuntime() {
        return runtime;
    }


    public List<Language> getSpokenLanguages() {
        return spokenLanguages;
    }


    public String getTagline() {
        return tagline;
    }


    public float getVoteAverage() {
        return voteAverage;
    }


    public int getVoteCount() {
        return voteCount;
    }


    public String getStatus() {
        return status;
    }


    public List<AlternativeTitle> getAlternativeTitles() {
        return alternativeTitles != null ? alternativeTitles.getTitles() : null;
    }


    public List<PersonCast> getCast() {
        return credits != null ? credits.getCast() : null;
    }


    public List<PersonCrew> getCrew() {
        return credits != null ? credits.getCrew() : null;
    }


    public List<Artwork> getImages() {
        return images != null ? images.getAll() : null;
    }


    public List<Keyword> getKeywords() {
        return keywords != null ? keywords.getKeywords() : null;
    }


    public List<ReleaseInfo> getReleases() {
        return releases != null ? releases.getCountries() : null;
    }


    public List<Trailer> getTrailers() {
        return trailers != null ? trailers.getAll() : null;
    }


    public List<Translation> getTranslations() {
        return translations != null ? translations.getTranslations() : null;
    }


    public List<MovieDb> getSimilarMovies() {
        return similarMovies != null ? similarMovies.getResults() : null;
    }


    public List<MovieList> getLists() {
        return lists != null ? lists.getResults() : null;
    }


    public List<Reviews> getReviews() {
        return reviews != null ? reviews.getResults() : null;
    }


    public Credits getCredits() {
        return credits;
    }


    public float getUserRating() {
        return userRating;
    }
}
