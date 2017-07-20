package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.model.people.Person;

import java.util.List;


/**
 * @author Holger Brandl
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class TvSeries extends AbstractTvElement implements Multi {

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
    private ResultsPage<TvSeries> recommendations;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("status")
    private String status;


    public List<Person> getCreatedBy() {
        return createdBy;
    }


    public List<Integer> getEpisodeRuntime() {
        return episodeRuntime;
    }


    public String getFirstAirDate() {
        return firstAirDate;
    }


    public String getLastAirDate() {
        return lastAirDate;
    }


    public List<Genre> getGenres() {
        return genres;
    }


    public String getHomepage() {
        return homepage;
    }


    public String getOriginalName() {
        return originalName;
    }


    public List<String> getOriginCountry() {
        return originCountry;
    }


    public List<Network> getNetworks() {
        return networks;
    }


    public String getOverview() {
        return overview;
    }


    public float getPopularity() {
        return popularity;
    }


    public String getBackdropPath() {
        return backdropPath;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }


    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public List<TvSeason> getSeasons() {
        return seasons;
    }
    
    public ResultsPage<TvSeries> getRecommendations(){
    	return recommendations;
    }


    public void setSeasons(List<TvSeason> seasons) {
        this.seasons = seasons;
    }


    public float getUserRating() {
        return userRating;
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


	public void setNetworks(List<Network> networks ) {
		this.networks = networks;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

    @Override
    public MediaType getMediaType() {
        return MediaType.TV_SERIES;
    }
}
