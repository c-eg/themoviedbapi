package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
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
}
