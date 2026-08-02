package uk.co.conoregan.themoviedbapi.model.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.keywords.Keyword;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvKeywords extends Results<Keyword> {
}
