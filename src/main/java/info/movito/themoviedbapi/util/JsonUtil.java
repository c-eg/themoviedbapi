package info.movito.themoviedbapi.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * JSON Utility.
 */
public final class JsonUtil {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ObjectWriter MAP_WRITER = OBJECT_MAPPER.writerFor(Map.class);

    private JsonUtil() {
    }

    /**
     * Converts a Map to a JSON String.
     *
     * @param map the map to convert.
     * @return the json.
     */
    public static String toJson(Map<String, ?> map) {
        try {
            return MAP_WRITER.writeValueAsString(map);
        }
        catch (JsonProcessingException jpe) {
            throw new RuntimeException("json conversion failed", jpe);
        }
    }
}
