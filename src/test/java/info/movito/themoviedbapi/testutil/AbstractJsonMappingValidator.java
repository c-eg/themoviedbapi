package info.movito.themoviedbapi.testutil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validates an {@link AbstractJsonMapping} object's fields.
 */
public class AbstractJsonMappingValidator {
    private final List<String> nullFields = new ArrayList<>();
    private final List<String> emptyCollections = new ArrayList<>();
    private final List<String> nullContainingCollection = new ArrayList<>();
    private final List<String> emptyMaps = new ArrayList<>();
    private final List<String> nullContainingMaps = new ArrayList<>();
    private final Map<String, Map<String, Object>> newItems = new HashMap<>();

    /**
     * Creates a new instance of the validator.
     *
     * @param objectToCheck the object to check.
     */
    public AbstractJsonMappingValidator(AbstractJsonMapping objectToCheck) {
        try {
            processFields(objectToCheck, objectToCheck.getClass().getName());
        }
        catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Validates the object for: null fields, empty collections, collections containing null, empty maps, maps containing null,
     * and new items.
     */
    public void validateAll() {
        validateNullFields();
        validateEmptyCollections();
        validateNullContainingCollection();
        validateEmptyMaps();
        validateNullContainingMaps();
        validateNewItems();
    }

    /**
     * Validates the object for: null fields.
     */
    public void validateNullFields() {
        validateNullFields(Collections.emptyList());
    }

    /**
     * Validates the object for: null fields.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateNullFields(List<String> fieldsToIgnore) {
        nullFields.removeAll(fieldsToIgnore);
        assertTrue(nullFields.isEmpty(), "Null fields found: " + nullFields);
    }

    /**
     * Validates the object for: empty collections.
     */
    public void validateEmptyCollections() {
        validateEmptyCollections(Collections.emptyList());
    }

    /**
     * Validates the object for: empty collections.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateEmptyCollections(List<String> fieldsToIgnore) {
        emptyCollections.removeAll(fieldsToIgnore);
        assertTrue(emptyCollections.isEmpty(), "Empty collections found in: " + emptyCollections);
    }

    /**
     * Validates the object for: collections containing null.
     */
    public void validateNullContainingCollection() {
        validateNullContainingCollection(Collections.emptyList());
    }

    /**
     * Validates the object for: collections containing null.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateNullContainingCollection(List<String> fieldsToIgnore) {
        nullContainingCollection.removeAll(fieldsToIgnore);
        assertTrue(nullContainingCollection.isEmpty(), "Collections containing null found: " + nullContainingCollection);
    }

    /**
     * Validates the object for: empty maps.
     */
    public void validateEmptyMaps() {
        validateEmptyMaps(Collections.emptyList());
    }

    /**
     * Validates the object for: empty maps.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateEmptyMaps(List<String> fieldsToIgnore) {
        emptyMaps.removeAll(fieldsToIgnore);
        assertTrue(emptyMaps.isEmpty(), "Empty maps found: " + emptyMaps);
    }

    /**
     * Validates the object for: maps containing null.
     */
    public void validateNullContainingMaps() {
        validateNullContainingMaps(Collections.emptyList());
    }

    /**
     * Validates the object for: maps containing null.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateNullContainingMaps(List<String> fieldsToIgnore) {
        nullContainingMaps.removeAll(fieldsToIgnore);
        assertTrue(nullContainingMaps.isEmpty(), "Maps containing null found: " + nullContainingMaps);
    }

    /**
     * Validates the object for: new items.
     */
    public void validateNewItems() {
        String newItemsString = newItems.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).toList().toString();
        assertTrue(newItems.isEmpty(), "New items found: " + newItemsString);
    }

    private void processFields(AbstractJsonMapping objectToCheck, String fieldName) throws IllegalAccessException {
        Map<String, Object> newItems = objectToCheck.getNewItems();
        if (!newItems.isEmpty()) {
            this.newItems.put(fieldName, newItems);
        }

        Class<?> clazz = objectToCheck.getClass();
        while (clazz != null && clazz != AbstractJsonMapping.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                String newFieldName = fieldName + "." + field.getName();
                Object fieldValue = field.get(objectToCheck);
                if (fieldValue == null) {
                    nullFields.add(newFieldName);
                }
                else if (fieldValue instanceof AbstractJsonMapping abstractJsonMapping) {
                    processFields(abstractJsonMapping, newFieldName);
                }
                else if (fieldValue instanceof Collection<?> collection) {
                    if (collection.isEmpty()) {
                        emptyCollections.add(newFieldName);
                    }
                    else {
                        Object item = collection.iterator().next();
                        if (item == null) {
                            nullContainingCollection.add(newFieldName);
                        }
                        else if (item instanceof AbstractJsonMapping abstractJsonMapping) {
                            processFields(abstractJsonMapping, newFieldName);
                        }
                    }
                }
                else if (fieldValue instanceof Map<?, ?> map) {
                    if (map.isEmpty()) {
                        emptyMaps.add(newFieldName);
                    }
                    else {
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            if (entry.getValue() == null) {
                                nullContainingMaps.add(newFieldName);
                            }
                            else if (entry.getValue() instanceof AbstractJsonMapping abstractJsonMapping) {
                                processFields(abstractJsonMapping, newFieldName);
                            }
                        }
                    }
                }
            }

            clazz = clazz.getSuperclass();
        }
    }
}
