package info.movito.themoviedbapi.model.core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.keywords.Keyword;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MovieKeywords extends AbstractJsonMapping {
    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<>();
}
