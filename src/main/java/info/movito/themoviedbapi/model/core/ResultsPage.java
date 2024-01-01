package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Iterator;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResultsPage<T> extends AbstractJsonMapping implements Iterable<T> {
    @JsonProperty("results")
    private List<T> results;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}
