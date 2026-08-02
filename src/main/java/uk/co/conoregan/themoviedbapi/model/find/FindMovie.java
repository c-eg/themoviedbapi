package uk.co.conoregan.themoviedbapi.model.find;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.Movie;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindMovie extends Movie {
}
