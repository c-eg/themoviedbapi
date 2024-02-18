package info.movito.themoviedbapi.util;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test utilities.
 */
public final class TestUtils {
    private TestUtils() {
    }

    /**
     * Reads a test file from the 'resources' folder.
     *
     * @param fileName the file name.
     * @return the file content.
     * @throws IOException if an error occurs.
     */
    public static String readTestFile(String fileName) throws IOException {
        try (InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IOException("File not found: " + fileName);
            }

            return IOUtils.toString(is, StandardCharsets.UTF_8);
        }
    }

    /**
     * Tests the given object for null fields and new items.
     */
    public static void testForNullFieldsAndNewItems(AbstractJsonMapping objectToCheck) {
        testForNullFields(objectToCheck);
        testForNewItems(objectToCheck);
    }

    /**
     * Tests the given object for null fields.
     */
    public static void testForNullFields(AbstractJsonMapping objectToCheck) {
        List<Field> nullFields = getNullFields(objectToCheck);
        List<String> nullFieldNames = nullFields.stream().map(Field::getName).toList();
        assertTrue(nullFields.isEmpty(), "Null fields found in object: " + nullFieldNames);
    }

    /**
     * Tests the given object for new items.
     */
    public static void testForNewItems(AbstractJsonMapping objectToCheck) {
        Map<String, Object> newItems = objectToCheck.getNewItems();
        assertTrue(newItems.isEmpty(), "Unknown properties found in object: " + newItems);
    }

    /**
     * Returns all the null fields in the given object.
     */
    private static List<Field> getNullFields(Object objectToCheck) {
        List<Field> nullFields = new ArrayList<>();

        Class<?> clazz = objectToCheck.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.get(objectToCheck) == null) {
                        nullFields.add(field);
                    }
                }
                catch (IllegalAccessException exception) {
                    throw new RuntimeException(exception);
                }
            }
            clazz = clazz.getSuperclass();
        }

        return nullFields;
    }
}
