package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PersonCredits extends AbstractJsonMapping {
    @JsonProperty("cast")
    private List<PersonCredit> cast;

    @JsonProperty("crew")
    private List<PersonCredit> crew;
}
