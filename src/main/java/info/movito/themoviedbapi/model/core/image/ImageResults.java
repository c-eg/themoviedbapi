package info.movito.themoviedbapi.model.core.image;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImageResults extends IdElement {
    @JsonProperty("logos")
    private List<Image> logos;
}
