package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;


public class ReleaseInfo extends AbstractJsonMapping {


    @JsonProperty("iso_3166_1")
    private String country;
    @JsonProperty("release_dates")
    private List<ReleaseDate> releaseDates;

    public String getCountry() {
        return country;
    }

    public List<ReleaseDate> getReleaseDates() { return releaseDates; }
}