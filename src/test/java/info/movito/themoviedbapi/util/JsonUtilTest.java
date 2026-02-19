package info.movito.themoviedbapi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link JsonUtil}.
 */
public class JsonUtilTest {
    /**
     * Tests {@link JsonUtil#toJson(Map)}, with string keys and string values.
     */
    @Test
    public void testToJson() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        String json = JsonUtil.toJson(map);
        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", json);
    }

    /**
     * Tests {@link JsonUtil#OBJECT_MAPPER} when there are unknown properties, does not throw an exception.
     */
    @Test
    public void testToJson_unknownProperties() {
        String content = """
            {
              "present": "test1",
              "stringList": [
                "one",
                "two"
              ],
              "unknown": "test2"
            }
            """;
        assertDoesNotThrow(() -> JsonUtil.OBJECT_MAPPER.readValue(content, JsonTestClass.class));
    }

    /**
     * Tests {@link JsonUtil#OBJECT_MAPPER} when a list property is null, it is deserialised as an empty list.
     */
    @Test
    public void testToJson_nullListPropertyDeserialisedAsEmpty() throws JsonProcessingException {
        String content = """
            {
              "present": "test1",
              "stringList": null
            }
            """;
        JsonTestClass result = JsonUtil.OBJECT_MAPPER.readValue(content, JsonTestClass.class);
        assertEquals(List.of(), result.getStringList());
    }

    @Data
    @EqualsAndHashCode
    private static class JsonTestClass {
        private String present;
        private List<String> stringList = new ArrayList<>();
    }
}
