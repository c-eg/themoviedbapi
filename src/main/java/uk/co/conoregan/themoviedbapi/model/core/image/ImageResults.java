package uk.co.conoregan.themoviedbapi.model.core.image;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImageResults extends IdElement {
    @JsonProperty("logos")
    private List<Image> logos = new ArrayList<>();
}
