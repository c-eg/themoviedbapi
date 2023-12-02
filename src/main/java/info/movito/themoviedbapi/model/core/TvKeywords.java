package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.keywords.Keyword;

import java.util.ArrayList;
import java.util.List;

public class TvKeywords extends AbstractJsonMapping {
    @JsonProperty("results")
    private List<Keyword> results = new ArrayList<Keyword>();

    public List<Keyword> getKeywords() {
        return results;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.results = keywords;
    }
}
