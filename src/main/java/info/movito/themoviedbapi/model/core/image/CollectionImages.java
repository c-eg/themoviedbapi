package info.movito.themoviedbapi.model.core.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

// todo: remove this
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionImages extends IdElement {
    @JsonProperty("backdrops")
    private List<Artwork> backdrops;

    @JsonProperty("posters")
    private List<Artwork> posters;

    @JsonProperty("profiles")
    private List<Artwork> profiles;

    @JsonProperty("logos")
    private List<Artwork> logos;

    // needed for episode backdrops
    @JsonProperty("stills")
    private List<Artwork> stills;
}
