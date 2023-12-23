package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Holger Brandl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListItemStatus extends AbstractJsonMapping {
    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("item_present")
    private Boolean itemPresent;
}
