package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

public class AlternativeTitle extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String country;

    @JsonProperty("title")
    private String title;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
