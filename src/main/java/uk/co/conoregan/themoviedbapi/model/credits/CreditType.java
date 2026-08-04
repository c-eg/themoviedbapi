package uk.co.conoregan.themoviedbapi.model.credits;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CreditType {
    @JsonProperty("cast")
    CAST,

    @JsonProperty("crew")
    CREW
}
