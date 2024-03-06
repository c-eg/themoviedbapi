package info.movito.themoviedbapi.util;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        testForNestedEmptyCollectionsAndNullObjects(objectToCheck);
    }

    /**
     * Tests the given object for null fields.
     *
     * @param objectToCheck the object to check for null fields.
     * @param fieldsToIgnore the fields to ignore.
     */
    public static void testForNullFields(AbstractJsonMapping objectToCheck, String... fieldsToIgnore) {
        List<Field> nullFields = getNullFields(objectToCheck, fieldsToIgnore);
        List<String> nullFieldNames = nullFields.stream().map(Field::getName).toList();
        assertTrue(nullFields.isEmpty(), "Null fields found in " + objectToCheck.getClass().getSimpleName() + ": " + nullFieldNames);
    }

    /**
     * Tests the given object for new items.
     */
    public static void testForNewItems(AbstractJsonMapping objectToCheck) {
        Map<String, Object> newItems = objectToCheck.getNewItems();
        assertTrue(newItems.isEmpty(), "Unknown properties found in " + objectToCheck.getClass().getSimpleName() + ": " + newItems);
    }

    /**
     * Tests the given object's fields for instances of objects, collections or maps. If there are any, it tests them for null fields and
     * new items.
     */
    public static void testForNestedEmptyCollectionsAndNullObjects(Object objectToCheck) {
        for (Field field : objectToCheck.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object fieldValue = field.get(objectToCheck);
                if (fieldValue != null) {
                    if (fieldValue instanceof AbstractJsonMapping abstractJsonMapping) {
                        testForNullFieldsAndNewItems(abstractJsonMapping);
                    }
                    else if (fieldValue instanceof Collection<?> collection) {
                        assertFalse(collection.isEmpty(), "Empty collection found in " + objectToCheck.getClass().getName() + "." +
                            field.getName());

                        Object item = collection.iterator().next();
                        if (item instanceof AbstractJsonMapping abstractJsonMapping) {
                            testForNullFieldsAndNewItems(abstractJsonMapping);
                        }

                        assertNotNull(item, "Null item found in " + objectToCheck.getClass().getName() + "." + field.getName());

                    }
                    else if (fieldValue instanceof Map<?, ?> map) {
                        assertFalse(map.isEmpty(), "Empty map found in " + objectToCheck.getClass().getName() + "." + field.getName());

                        for (Object item : map.values()) {
                            if (item instanceof AbstractJsonMapping abstractJsonMapping) {
                                testForNullFieldsAndNewItems(abstractJsonMapping);
                            }

                            assertNotNull(item, "Null item found in " + objectToCheck.getClass().getName() + "." + field.getName());
                        }
                    }
                }
            }
            catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * Returns all the null fields in the given object.
     */
    private static List<Field> getNullFields(Object objectToCheck, String... fieldsToIgnore) {
        List<Field> nullFields = new ArrayList<>();

        Class<?> clazz = objectToCheck.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (fieldsToIgnore != null && List.of(fieldsToIgnore).contains(field.getName())) {
                    continue;
                }

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
