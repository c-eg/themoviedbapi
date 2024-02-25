package info.movito.themoviedbapi.model.people.credits;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MediaType {
    @JsonProperty("movie")
    MOVIE,

    @JsonProperty("tv")
    TV
}
