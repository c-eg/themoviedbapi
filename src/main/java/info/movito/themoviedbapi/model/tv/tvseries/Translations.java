package info.movito.themoviedbapi.model.tv.tvseries;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Translations extends IdElement {
    @JsonProperty("translations")
    private List<Translation> translations;
}
