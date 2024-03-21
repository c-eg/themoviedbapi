package info.movito.themoviedbapi.model.tv.core.credits;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AggregateCredits extends IdElement {
    @JsonProperty("cast")
    private List<AggregateCast> cast;

    @JsonProperty("crew")
    private List<AggregateCrew> crew;
}
