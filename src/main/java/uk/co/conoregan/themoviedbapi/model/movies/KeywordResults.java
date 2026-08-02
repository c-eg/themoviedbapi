package uk.co.conoregan.themoviedbapi.model.movies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;
import uk.co.conoregan.themoviedbapi.model.keywords.Keyword;

@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordResults extends IdElement {
    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<>();
}
