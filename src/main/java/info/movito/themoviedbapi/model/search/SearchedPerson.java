package info.movito.themoviedbapi.model.search;

import info.movito.themoviedbapi.model.core.popularperson.PopularPerson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchedPerson extends PopularPerson implements Multi {
    @Override
    public MediaType getMediaType() {
        return MediaType.PERSON;
    }
}
