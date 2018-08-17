package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

public class ReleaseDate extends AbstractJsonMapping {

    @JsonProperty("iso_639_1")
    private String language;
    @JsonProperty("certification")
    private String certification;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("note")
    private String note;
    @JsonProperty("type")
    private String type;


    public String getNote() { return note; }

    public String getType() { return type; }

    public String getCertification() {
        return certification;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


}
