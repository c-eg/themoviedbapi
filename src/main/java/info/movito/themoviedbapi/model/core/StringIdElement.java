package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Base class for json wrappers with string id element.
 *
 * @author Holger Brandl
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StringIdElement extends AbstractJsonMapping implements Serializable {
    @JsonProperty("id")
    private String id;
}
