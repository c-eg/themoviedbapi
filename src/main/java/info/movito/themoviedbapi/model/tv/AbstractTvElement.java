package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.ExternalIds;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.core.NamedIdElement;


public class AbstractTvElement extends NamedIdElement {


    // Appendable responses for all tv elements

    @JsonProperty("credits")
    private Credits credits;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @JsonProperty("images")
    private MovieImages images;


    public Credits getCredits() {
        return credits;
    }


    public ExternalIds getExternalIds() {
        return externalIds;
    }


    public MovieImages getImages() {
        return images;
    }
}
