package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * TODO: Maybe use ResultsPage class instead of this class.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Translations extends IdElement {
    @JsonProperty("translations")
    private List<Translation> translations;
}
