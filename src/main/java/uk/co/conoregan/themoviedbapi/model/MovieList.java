package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Getter
@Setter
public class MovieList extends IdElement {
    @JsonProperty("description")
    private String description;

    @JsonProperty("favorite_count")
    private Integer favoriteCount;

    @JsonProperty("item_count")
    private Integer itemCount;

    @JsonProperty("iso_639_1")
    private String iso639;

    @JsonProperty("list_type")
    private String listType;

    @JsonProperty("name")
    private String name;

    @JsonProperty("poster_path")
    private String posterPath;
}
