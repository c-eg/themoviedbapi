package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

@Setter
@Getter
public class ResultsPage<T> extends AbstractJsonMapping implements Iterable<T> {
    @JsonProperty("results")
    private List<T> results;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}
