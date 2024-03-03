package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.people.credits.MovieCast;
import info.movito.themoviedbapi.model.people.credits.MovieCrew;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

// todo remove this
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCredits extends IdElement {
    @JsonProperty("cast")
    private List<MovieCast> cast;

    @JsonProperty("crew")
    private List<MovieCrew> crew;
}
