package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Base class for json wrappers with id element.
 *
 * @author Holger Brandl
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class IdElement extends AbstractJsonMapping implements Serializable {
    @JsonProperty("id")
    private int id;
}
