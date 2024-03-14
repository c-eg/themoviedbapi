package info.movito.themoviedbapi.model.tv.tvseries;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
