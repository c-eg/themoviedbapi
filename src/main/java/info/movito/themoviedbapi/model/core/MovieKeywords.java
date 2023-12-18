package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.keywords.Keyword;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieKeywords extends AbstractJsonMapping {
    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<>();
}
