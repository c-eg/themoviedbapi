package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.IdElement;
import org.apache.commons.lang3.StringUtils;


@JsonRootName("collection")
public class Collection extends IdElement {


    @JsonProperty("title")
    private String title;
    @JsonProperty("name")
    private String name;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("release_date")
    private String releaseDate;


    public String getBackdropPath() {
        return backdropPath;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public String getReleaseDate() {
        return releaseDate;
    }


    public String getTitle() {
        if (StringUtils.isBlank(title)) {
            return name;
        }
        return title;
    }


    public String getName() {
        if (StringUtils.isBlank(name)) {
            return title;
        }
        return name;
    }


    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setName(String name) {
        this.name = name;
    }

}
