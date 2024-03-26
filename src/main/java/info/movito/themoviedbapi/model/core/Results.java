package info.movito.themoviedbapi.model.core;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Results<T> extends IdElement implements Iterable<T> {
    @JsonProperty("results")
    private List<T> results;

    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}
