package uk.co.conoregan.themoviedbapi.model.movies.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

public class ChangedMovie extends IdElement {
    @JsonProperty("adult")
    private boolean adult;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}
