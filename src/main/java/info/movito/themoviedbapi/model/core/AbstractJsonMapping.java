package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Holger Brandl
 */
@Getter
public abstract class AbstractJsonMapping implements Serializable {
    private final Map<String, Object> unknownProperties = new HashMap<>();

    /**
     * Handle unknown properties and print a message.
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        unknownProperties.put(key, value);

        String message = String.format("Unknown property: '%s', value: '%s'", key, value);
        getLogger(getClass()).debug(message);
    }

    private static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
