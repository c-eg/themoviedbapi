package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.keywords.Keyword;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieKeywords extends AbstractJsonMapping {
    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<>();
}
