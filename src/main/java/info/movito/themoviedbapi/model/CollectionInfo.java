package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;

import java.util.List;


public class CollectionInfo extends NamedIdElement {


    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("parts")
    private List<Collection> parts;


    public String getBackdropPath() {
        return backdropPath;
    }


    public String getOverview() {
        return overview;
    }


    public List<Collection> getParts() {
        return parts;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public void setOverview(String overview) {
        this.overview = overview;
    }


    public void setParts(List<Collection> parts) {
        this.parts = parts;
    }


    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}
