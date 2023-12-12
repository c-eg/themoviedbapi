package uk.co.conoregan.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import uk.co.conoregan.themoviedbapi.model.ContentRating;
import uk.co.conoregan.themoviedbapi.model.Genre;
import uk.co.conoregan.themoviedbapi.model.Multi;
import uk.co.conoregan.themoviedbapi.model.core.ResultsPage;
import uk.co.conoregan.themoviedbapi.model.people.Person;

import java.util.List;

/**
 * @author Holger Brandl
 */
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
    private float popularity;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("number_of_episodes")
    private int numberOfEpisodes;

    @JsonProperty("number_of_seasons")
    private int numberOfSeasons;

    @JsonProperty("seasons")
    private List<TvSeason> seasons;

    @JsonProperty("recommendations")
    private ResultsPage<TvSeriesDb> recommendations;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("content_ratings")
    private ContentRating.Results contentRatings;

    public List<Person> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<Person> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getEpisodeRuntime() {
        return episodeRuntime;
    }

    public void setEpisodeRuntime(List<Integer> episodeRuntime) {
        this.episodeRuntime = episodeRuntime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<TvSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<TvSeason> seasons) {
        this.seasons = seasons;
    }

    public ResultsPage<TvSeriesDb> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(ResultsPage<TvSeriesDb> recommendations) {
        this.recommendations = recommendations;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
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

    @Override
    public MediaType getMediaType() {
        return MediaType.TV_SERIES;
    }

    public List<ContentRating> getContentRatings() {
        return contentRatings != null ? contentRatings.getContentRatings() : null;
    }

    public void setContentRatings(ContentRating.Results contentRatings) {
        this.contentRatings = contentRatings;
    }
}
