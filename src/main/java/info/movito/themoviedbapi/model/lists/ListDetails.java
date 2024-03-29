package info.movito.themoviedbapi.model.lists;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListDetails extends NamedIdElement {
    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("description")
    private String description;

    @JsonProperty("favorite_count")
    private Integer favoriteCount;

    @JsonProperty("items")
    private List<Movie> items;

    @JsonProperty("item_count")
    private Integer itemCount;

    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("poster_path")
    private String posterPath;
}
