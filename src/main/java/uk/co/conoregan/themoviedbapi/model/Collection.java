package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;
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

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets the title, or name, if the title is blank.
     *
     * @return the title or name.
     */
    public String getTitle() {
        if (StringUtils.isBlank(title)) {
            return name;
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the name, or title, if the name is blank.
     *
     * @return the title or name.
     */
    public String getName() {
        if (StringUtils.isBlank(name)) {
            return title;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
