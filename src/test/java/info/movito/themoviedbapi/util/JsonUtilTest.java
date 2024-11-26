package info.movito.themoviedbapi.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link JsonUtil}.
 */
public class JsonUtilTest {
    /**
     * Tests {@link JsonUtil#toJson(Map)}, with string keys and string values.
     */
    @Test
    public void testToJsonStringString() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        String json = JsonUtil.toJson(map);
        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", json);
    }

    /**
     * Tests {@link JsonUtil#toJson(Map)}, with string keys and boolean values.
     */
    @Test
    public void testToJsonStringBoolean() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("key1", true);
        map.put("key2", false);

        String json = JsonUtil.toJson(map);
        assertEquals("{\"key1\":true,\"key2\":false}", json);
    }

    /**
     * Tests {@link JsonUtil#toJson(Map)}, with string keys and integer values.
     */
    @Test
    public void testToJsonStringInteger() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

        String json = JsonUtil.toJson(map);
        assertEquals("{\"key1\":1,\"key2\":2}", json);
    }
}
