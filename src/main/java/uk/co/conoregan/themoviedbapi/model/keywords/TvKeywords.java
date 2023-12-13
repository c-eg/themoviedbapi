package uk.co.conoregan.themoviedbapi.model.keywords;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvKeywords extends AbstractJsonMapping {
    @JsonProperty("results")
    private List<Keyword> results = new ArrayList<>();
}
