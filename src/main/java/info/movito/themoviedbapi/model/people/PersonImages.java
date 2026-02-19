package info.movito.themoviedbapi.model.people;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.image.Artwork;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonImages extends IdElement {
    @JsonProperty("profiles")
    private List<Artwork> profiles = new ArrayList<>();
}
