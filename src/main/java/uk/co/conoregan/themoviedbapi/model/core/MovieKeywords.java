package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.keywords.Keyword;

import java.util.ArrayList;
import java.util.List;

public class MovieKeywords extends AbstractJsonMapping {
    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<Keyword>();

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
