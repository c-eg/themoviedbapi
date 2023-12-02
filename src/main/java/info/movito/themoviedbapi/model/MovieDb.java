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

import java.util.List;

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
    private MovieTranslations translations;

    @JsonProperty("similar")
    private ResultsPage<MovieDb> similarMovies;

    @JsonProperty("recommendations")
    private ResultsPage<MovieDb> recommendedMovies;

    @JsonProperty("reviews")
    private ResultsPage<Reviews> reviews;

    @JsonProperty("lists")
    private ResultsPage<MovieList> lists;

    @JsonProperty("watch/providers")
    private ProviderResults watchProviders;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Collection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(Collection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<Language> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AlternativeTitle> getAlternativeTitles() {
        return alternativeTitles != null ? alternativeTitles.getTitles() : null;
    }

    public void setAlternativeTitles(MoviesAlternativeTitles alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
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

    public void setKeywords(MovieKeywords keywords) {
        this.keywords = keywords;
    }

    public List<ReleaseInfo> getReleases() {
        return releases != null ? releases.getResults() : null;
    }

    public void setReleases(TmdbMovies.ReleaseInfoResults releases) {
        this.releases = releases;
    }

    public List<Video> getVideos() {
        return videos != null ? videos.getVideos() : null;
    }

    public void setVideos(Video.Results videos) {
        this.videos = videos;
    }

    public List<Translation> getTranslations() {
        return translations != null ? translations.getTranslations() : null;
    }

    public void setTranslations(MovieTranslations translations) {
        this.translations = translations;
    }

    public List<MovieDb> getSimilarMovies() {
        return similarMovies != null ? similarMovies.getResults() : null;
    }

    public void setSimilarMovies(ResultsPage<MovieDb> similarMovies) {
        this.similarMovies = similarMovies;
    }

    public List<MovieDb> getRecommendations() {
        return recommendedMovies != null ? recommendedMovies.getResults() : null;
    }

    public List<MovieList> getLists() {
        return lists != null ? lists.getResults() : null;
    }

    public void setLists(ResultsPage<MovieList> lists) {
        this.lists = lists;
    }

    public List<Reviews> getReviews() {
        return reviews != null ? reviews.getResults() : null;
    }

    public void setReviews(ResultsPage<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public ProviderResults getWatchProviders() {
        return watchProviders;
    }

    public void setWatchProviders(ProviderResults watchProviders) {
        this.watchProviders = watchProviders;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    @Override
    public String toString() {
        return title + " - " + releaseDate;
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }

    public void setImages(MovieImages images) {
        this.images = images;
    }
}
