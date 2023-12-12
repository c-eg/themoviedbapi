package uk.co.conoregan.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

public class PersonCredits extends AbstractJsonMapping {
    @JsonProperty("cast")
    private List<PersonCredit> cast;

    @JsonProperty("crew")
    private List<PersonCredit> crew;

    public List<PersonCredit> getCast() {
        return cast;
    }

    public void setCast(List<PersonCredit> cast) {
        this.cast = cast;
    }

    public List<PersonCredit> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonCredit> crew) {
        this.crew = crew;
    }
}
