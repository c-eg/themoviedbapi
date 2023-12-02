package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Holger Brandl
 */
public abstract class AbstractJsonMapping implements Serializable {
    private static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * Handle unknown properties and print a message.
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        String message = "Unknown property: '" + key + "' value: '" + value + "'";
        getLogger(this.getClass()).trace(message);
    }
}
