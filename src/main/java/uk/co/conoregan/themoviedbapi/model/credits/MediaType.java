package uk.co.conoregan.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MediaType {
    @JsonProperty("movie")
    MOVIE,

    @JsonProperty("person")
    PERSON,

    @JsonProperty("tv")
    TV,

    @JsonProperty("tv_episode")
    TV_EPISODE,

    @JsonProperty("tv_season")
    TV_SEASON
}
