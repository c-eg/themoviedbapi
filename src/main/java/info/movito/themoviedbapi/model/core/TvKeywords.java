package info.movito.themoviedbapi.model.core;

import info.movito.themoviedbapi.model.keywords.Keyword;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvKeywords extends Results<Keyword> {
}
