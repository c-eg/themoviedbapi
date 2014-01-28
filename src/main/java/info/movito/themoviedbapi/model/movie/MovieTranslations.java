package info.movito.themoviedbapi.model.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Translation;
import info.movito.themoviedbapi.model.core.IdElement;

import java.util.List;


public class MovieTranslations extends IdElement {

    @JsonProperty("translations")
    private List<Translation> translations;


    public List<Translation> getTranslations() {
        return translations;
    }
}
