package uk.co.conoregan.themoviedbapi.model.collections;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;
import uk.co.conoregan.themoviedbapi.model.core.image.Artwork;

@Data
@EqualsAndHashCode(callSuper = true)
public class Images extends IdElement {
    @JsonProperty("backdrops")
    private List<Artwork> backdrops = new ArrayList<>();

    @JsonProperty("posters")
    private List<Artwork> posters = new ArrayList<>();
}
