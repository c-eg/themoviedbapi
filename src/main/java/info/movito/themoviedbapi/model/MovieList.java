package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieList extends NamedStringIdElement {
    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("description")
    private String description;

    @JsonProperty("favorite_count")
    private int favoriteCount;

    @JsonProperty("item_count")
    private int itemCount;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("list_type")
    private String listType;

    //used for /list
    @JsonProperty("items")
    private List<MovieDb> items;
}
