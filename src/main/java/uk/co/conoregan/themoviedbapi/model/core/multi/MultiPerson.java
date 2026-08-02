package uk.co.conoregan.themoviedbapi.model.core.multi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.popularperson.PopularPerson;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultiPerson extends PopularPerson implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.PERSON;
    }
}
