package info.movito.themoviedbapi.util;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validates an {@link AbstractJsonMapping} object's fields.
 */
public class AbstractJsonMappingValidator {
    private final Map<String, List<Field>> nullFields = new HashMap<>();
    private final Map<String, List<Field>> emptyCollections = new HashMap<>();
    private final Map<String, List<Field>> nullContainingCollection = new HashMap<>();
    private final Map<String, List<Field>> emptyMaps = new HashMap<>();
    private final Map<String, List<Field>> nullContainingMaps = new HashMap<>();
    private final Map<String, Map<String, Object>> newItems = new HashMap<>();

    /**
     * Creates a new instance of the validator.
     *
     * @param objectToCheck the object to check.
     */
    public AbstractJsonMappingValidator(AbstractJsonMapping objectToCheck) {
        try {
            addNullAndEmptyFields(objectToCheck, null);
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
        assertTrue(nullFields.isEmpty(), "Null fields found: " + getFormattedFieldNames(nullFields));
    }

    /**
     * Validates the object for: null fields.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateNullFields(List<FieldsToIgnore> fieldsToIgnore) {
        for (FieldsToIgnore fieldToIgnore : fieldsToIgnore) {
            filterFieldsToIgnore(nullFields, fieldToIgnore.clazz(), fieldToIgnore.initialFieldNameFromObjectToCheck(),
                fieldToIgnore.nullFieldsToIgnore());
        }
        assertTrue(nullFields.isEmpty(), "Null fields found: " + getFormattedFieldNames(nullFields));
    }

    /**
     * Validates the object for: empty collections.
     */
    public void validateEmptyCollections() {
        assertTrue(emptyCollections.isEmpty(), "Empty collections found in: " + getFormattedFieldNames(emptyCollections));
    }

    /**
     * Validates the object for: empty collections.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateEmptyCollections(List<FieldsToIgnore> fieldsToIgnore) {
        for (FieldsToIgnore fieldToIgnore : fieldsToIgnore) {
            filterFieldsToIgnore(emptyCollections, fieldToIgnore.clazz(), fieldToIgnore.initialFieldNameFromObjectToCheck(),
                fieldToIgnore.nullFieldsToIgnore());
        }
        assertTrue(emptyCollections.isEmpty(), "Empty collections found in: " + getFormattedFieldNames(emptyCollections));
    }

    /**
     * Validates the object for: collections containing null.
     */
    public void validateNullContainingCollection() {
        assertTrue(nullContainingCollection.isEmpty(), "Collections containing null found: " +
            getFormattedFieldNames(nullContainingCollection));
    }

    /**
     * Validates the object for: collections containing null.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateNullContainingCollection(List<FieldsToIgnore> fieldsToIgnore) {
        for (FieldsToIgnore fieldToIgnore : fieldsToIgnore) {
            filterFieldsToIgnore(nullContainingCollection, fieldToIgnore.clazz(), fieldToIgnore.initialFieldNameFromObjectToCheck(),
                fieldToIgnore.nullFieldsToIgnore());
        }
        assertTrue(nullContainingCollection.isEmpty(), "Collections containing null found: " +
            getFormattedFieldNames(nullContainingCollection));
    }

    /**
     * Validates the object for: empty maps.
     */
    public void validateEmptyMaps() {
        assertTrue(emptyMaps.isEmpty(), "Empty maps found: " + getFormattedFieldNames(emptyMaps));
    }

    /**
     * Validates the object for: empty maps.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateEmptyMaps(List<FieldsToIgnore> fieldsToIgnore) {
        for (FieldsToIgnore fieldToIgnore : fieldsToIgnore) {
            filterFieldsToIgnore(emptyMaps, fieldToIgnore.clazz(), fieldToIgnore.initialFieldNameFromObjectToCheck(),
                fieldToIgnore.nullFieldsToIgnore());
        }
        assertTrue(emptyMaps.isEmpty(), "Empty maps found: " + getFormattedFieldNames(emptyMaps));
    }

    /**
     * Validates the object for: maps containing null.
     */
    public void validateNullContainingMaps() {
        assertTrue(nullContainingMaps.isEmpty(), "Maps containing null found: " + getFormattedFieldNames(nullContainingMaps));
    }

    /**
     * Validates the object for: maps containing null.
     *
     * @param fieldsToIgnore the fields to ignore.
     */
    public void validateNullContainingMaps(List<FieldsToIgnore> fieldsToIgnore) {
        for (FieldsToIgnore fieldToIgnore : fieldsToIgnore) {
            filterFieldsToIgnore(nullContainingMaps, fieldToIgnore.clazz(), fieldToIgnore.initialFieldNameFromObjectToCheck(),
                fieldToIgnore.nullFieldsToIgnore());
        }
        assertTrue(nullContainingMaps.isEmpty(), "Maps containing null found: " + getFormattedFieldNames(nullContainingMaps));
    }

    /**
     * Validates the object for: new items.
     */
    public void validateNewItems() {
        assertTrue(newItems.isEmpty(), "New items found: " + getFormattedFieldNamesForNewItems(newItems));
    }

    private void filterFieldsToIgnore(Map<String, List<Field>> map, Class<?> clazz, String initialFieldNameFromObjectToCheck,
                                      String... fieldsToIgnore) {
        String key = clazz.getName() + "." + initialFieldNameFromObjectToCheck;

        Iterator<Map.Entry<String, List<Field>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Field>> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                List<Field> fields = entry.getValue();
                for (String fieldToIgnore : fieldsToIgnore) {
                    fields.removeIf(field -> field.getName().equals(fieldToIgnore));
                }
            }

            if (entry.getValue().isEmpty()) {
                iterator.remove();
            }
        }
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
                    mergeMapWithNew(nullFields, objectToCheck, field, parentField);
                }
                else if (fieldValue instanceof AbstractJsonMapping abstractJsonMapping) {
                    addNullAndEmptyFields(abstractJsonMapping, parentField);
                }
                else if (fieldValue instanceof Collection<?> collection) {
                    if (collection.isEmpty()) {
                        mergeMapWithNew(emptyCollections, objectToCheck, field, parentField);
                    }
                    else {
                        Object item = collection.iterator().next();
                        if (item == null) {
                            mergeMapWithNew(nullContainingCollection, objectToCheck, field, parentField);
                        }
                        else if (item instanceof AbstractJsonMapping abstractJsonMapping) {
                            addNullAndEmptyFields(abstractJsonMapping, parentField);
                        }
                    }
                }
                else if (fieldValue instanceof Map<?, ?> map) {
                    if (map.isEmpty()) {
                        mergeMapWithNew(emptyMaps, objectToCheck, field, parentField);
                    }
                    else {
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            if (entry.getValue() == null) {
                                mergeMapWithNew(nullContainingMaps, objectToCheck, field, parentField);
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

    private void mergeMapWithNew(Map<String, List<Field>> map, AbstractJsonMapping objectToCheck, Field field, Field parentField) {
        map.merge(getMapKey(objectToCheck, parentField), new ArrayList<>(List.of(field)), (oldValue, newValue) -> {
            oldValue.addAll(newValue);
            return oldValue;
        });
    }

    private String getMapKey(AbstractJsonMapping objectToCheck, Field initialField) {
        StringBuilder key = new StringBuilder();
        key.append(objectToCheck.getClass().getName());
        if (initialField != null) {
            key.append('.').append(initialField.getName());
        }
        return key.toString();
    }

    private String getFormattedFieldNames(Map<String, List<Field>> fields) {
        return fields.entrySet().stream().map(entry -> entry.getKey() + ": " +
            entry.getValue().stream().map(Field::getName).toList()).toList().toString();
    }

    private String getFormattedFieldNamesForNewItems(Map<String, Map<String, Object>> newItems) {
        return newItems.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).toList().toString();
    }
}
