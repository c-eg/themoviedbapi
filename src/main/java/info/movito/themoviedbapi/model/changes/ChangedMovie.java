package info.movito.themoviedbapi.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;


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
