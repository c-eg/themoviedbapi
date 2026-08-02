package uk.co.conoregan.themoviedbapi.model.people.credits;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvCredits extends IdElement {
    @JsonProperty("cast")
    private List<TvCast> cast = new ArrayList<>();

    @JsonProperty("crew")
    private List<TvCrew> crew = new ArrayList<>();
}
