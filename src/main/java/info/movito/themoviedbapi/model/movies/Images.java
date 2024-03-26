package info.movito.themoviedbapi.model.movies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.image.Artwork;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Images extends IdElement {
    @JsonProperty("backdrops")
    private List<Artwork> backdrops;

    @JsonProperty("logos")
    private List<Artwork> logos;

    @JsonProperty("posters")
    private List<Artwork> posters;
}
