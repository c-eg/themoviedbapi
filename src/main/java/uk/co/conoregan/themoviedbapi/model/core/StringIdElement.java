package uk.co.conoregan.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Base class for json wrappers with id element.
 * TODO: Maybe remove this class.
 *
 * @author Holger Brandl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StringIdElement extends AbstractJsonMapping implements Serializable {
    @JsonProperty("id")
    private String id;
}
