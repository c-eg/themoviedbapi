package uk.co.conoregan.themoviedbapi.model.tv.season;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeResults extends AbstractJsonMapping {
    @JsonProperty("changes")
    private List<Change> changedItems = new ArrayList<>();
}
