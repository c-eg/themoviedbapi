package info.movito.themoviedbapi.model.tv.season;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Images extends IdElement {
    @JsonProperty("posters")
    private List<Artwork> posters;
}
