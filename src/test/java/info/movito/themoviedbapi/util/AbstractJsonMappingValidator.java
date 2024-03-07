package info.movito.themoviedbapi.util;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Todo:
     - Change current usages of existing TestUtils methods for checking fields to use ObjectValidator in some form
 */

/**
 * Validates an {@link AbstractJsonMapping} object's fields.
 */
public class AbstractJsonMappingValidator {
    private final Map<String, Field> nullFields = new HashMap<>();
    private final Map<String, Field> emptyCollections = new HashMap<>();
    private final Map<String, Field> nullContainingCollection = new HashMap<>();
    private final Map<String, Field> emptyMaps = new HashMap<>();
    private final Map<String, Field> nullContainingMaps = new HashMap<>();
    private final Map<String, Map<String, Object>> newItems = new HashMap<>();

    /**
     * Creates a new instance of the validator.
     *
     * @param objectToCheck the object to check.
     * @throws IllegalAccessException if an error occurs.
     */
    public AbstractJsonMappingValidator(AbstractJsonMapping objectToCheck) throws IllegalAccessException {
        addNullAndEmptyFields(objectToCheck, null);
    }

    /**
     * Validates the object for: null fields, empty collections, collections containing null, empty maps, maps containing null,
     * and new items.
     */
    public void validateAll() {
        assertTrue(nullFields.isEmpty(), "Null fields found: " + getFormattedFieldNames(nullFields));
        assertTrue(emptyCollections.isEmpty(), "Empty collections found in: " + getFormattedFieldNames(emptyCollections));
        assertTrue(nullContainingCollection.isEmpty(), "Collections containing null found: " +
            getFormattedFieldNames(nullContainingCollection));
        assertTrue(emptyMaps.isEmpty(), "Empty maps found: " + getFormattedFieldNames(emptyMaps));
        assertTrue(nullContainingMaps.isEmpty(), "Maps containing null found: " + getFormattedFieldNames(nullContainingMaps));
        assertTrue(newItems.isEmpty(), "New items found: " + getFormattedFieldNamesForNewItems(newItems));
    }

    private void addNullAndEmptyFields(AbstractJsonMapping objectToCheck, Field initialField) throws IllegalAccessException {
        Class<?> clazz = objectToCheck.getClass();

        Map<String, Object> newItems = objectToCheck.getNewItems();
        if (!newItems.isEmpty()) {
            this.newItems.put(getMapKey(objectToCheck, initialField), newItems);
        }

        while (clazz != null && clazz != AbstractJsonMapping.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Object fieldValue = field.get(objectToCheck);
                Field parentField = initialField != null ? initialField : field;

                if (fieldValue == null) {
                    nullFields.put(getMapKey(objectToCheck, parentField), field);
                }
                else if (fieldValue instanceof AbstractJsonMapping abstractJsonMapping) {
                    addNullAndEmptyFields(abstractJsonMapping, parentField);
                }
                else if (fieldValue instanceof Collection<?> collection) {
                    if (collection.isEmpty()) {
                        emptyCollections.put(getMapKey(objectToCheck, parentField), field);
                    }
                    else {
                        Object item = collection.iterator().next();
                        if (item == null) {
                            nullContainingCollection.put(getMapKey(objectToCheck, parentField), field);
                        }
                        else if (item instanceof AbstractJsonMapping abstractJsonMapping) {
                            addNullAndEmptyFields(abstractJsonMapping, parentField);
                        }
                    }
                }
                else if (fieldValue instanceof Map<?, ?> map) {
                    if (map.isEmpty()) {
                        emptyMaps.put(getMapKey(objectToCheck, parentField), field);
                    }
                    else {
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            if (entry.getValue() == null) {
                                nullContainingMaps.put(getMapKey(objectToCheck, parentField), field);
                            }
                            else if (entry.getValue() instanceof AbstractJsonMapping abstractJsonMapping) {
                                addNullAndEmptyFields(abstractJsonMapping, parentField);
                            }
                        }
                    }
                }
            }

            clazz = clazz.getSuperclass();
        }
    }

    private String getMapKey(AbstractJsonMapping objectToCheck, Field initialField) {
        StringBuilder key = new StringBuilder();
        key.append(objectToCheck.getClass().getName());
        if (initialField != null) {
            key.append('.').append(initialField.getName());
        }
        return key.toString();
    }

    private String getFormattedFieldNamesForNewItems(Map<String, Map<String, Object>> newItems) {
        return newItems.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).toList().toString();
    }

    private String getFormattedFieldNames(Map<String, Field> fields) {
        return fields.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue().getName()).toList().toString();
    }
}
