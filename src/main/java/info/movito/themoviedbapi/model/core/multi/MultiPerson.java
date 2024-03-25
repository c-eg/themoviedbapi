package info.movito.themoviedbapi.model.core.multi;

import info.movito.themoviedbapi.model.core.popularperson.PopularPerson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultiPerson extends PopularPerson implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.PERSON;
    }
}
