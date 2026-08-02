package uk.co.conoregan.themoviedbapi.model.people;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;
import uk.co.conoregan.themoviedbapi.model.core.image.Artwork;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonImages extends IdElement {
    @JsonProperty("profiles")
    private List<Artwork> profiles = new ArrayList<>();
}
