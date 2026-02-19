package info.movito.themoviedbapi.model.tv.core.credits;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AggregateCredits extends IdElement {
    @JsonProperty("cast")
    private List<AggregateCast> cast = new ArrayList<>();

    @JsonProperty("crew")
    private List<AggregateCrew> crew = new ArrayList<>();
}
