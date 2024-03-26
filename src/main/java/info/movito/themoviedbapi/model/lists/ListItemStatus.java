package info.movito.themoviedbapi.model.lists;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Holger Brandl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListItemStatus extends IdElement {
    @JsonProperty("item_present")
    private Boolean itemPresent;
}
