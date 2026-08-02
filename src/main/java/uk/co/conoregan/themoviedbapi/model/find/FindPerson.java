package uk.co.conoregan.themoviedbapi.model.find;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.popularperson.PopularPerson;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindPerson extends PopularPerson {
}
