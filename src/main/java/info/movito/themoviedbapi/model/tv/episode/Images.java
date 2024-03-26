package info.movito.themoviedbapi.model.tv.episode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.image.Artwork;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Images extends IdElement {
    @JsonProperty("stills")
    private List<Artwork> stills;
}
