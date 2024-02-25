package info.movito.themoviedbapi.model.people.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CombinedPersonCredits extends IdElement {
    @JsonProperty("cast")
    private List<Cast> cast;

    @JsonProperty("crew")
    private List<Crew> crew;
}
