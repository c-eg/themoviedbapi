package info.movito.themoviedbapi.model.people.credits;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvCredits extends IdElement {
    @JsonProperty("cast")
    private List<TvCast> cast = new ArrayList<>();

    @JsonProperty("crew")
    private List<TvCrew> crew = new ArrayList<>();
}
