package uk.co.conoregan.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCredits extends AbstractJsonMapping {
    @JsonProperty("cast")
    private List<PersonCredit> cast;

    @JsonProperty("crew")
    private List<PersonCredit> crew;
}
