package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;

@Setter
@Getter
public class ResultsPage<T> extends AbstractJsonMapping implements Iterable<T> {
    @JsonProperty("results")
    private List<T> results;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_results")
    private Integer totalResults;

    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}
