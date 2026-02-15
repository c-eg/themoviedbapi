package info.movito.themoviedbapi.testutil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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

    private final ValidatorConfig validatorConfig;

    /**
     * Creates a new instance of the validator.
     *
     * @param objectToCheck the object to check.
     */
    public AbstractJsonMappingValidator(AbstractJsonMapping objectToCheck) {
        this(objectToCheck, ValidatorConfig.builder().build());
    }

    /**
     * Creates a new instance of the validator.
     *
     * @param objectToCheck   the object to check.
     * @param validatorConfig the validator's config.
     */
    public AbstractJsonMappingValidator(AbstractJsonMapping objectToCheck, ValidatorConfig validatorConfig) {
        this.validatorConfig = validatorConfig;

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

    private static Predicate<String> filterFieldsToIgnore(List<String> list) {
        return value -> !list.contains(value);
    }

    private void validateNullFields() {
        List<String> fieldsToProcess = nullFields.stream()
            .filter(filterFieldsToIgnore(validatorConfig.getNullFieldsToIgnore()))
            .toList();
        assertTrue(fieldsToProcess.isEmpty(), "Null fields found: %s. Fields ignored: %s"
            .formatted(fieldsToProcess, validatorConfig.getNullFieldsToIgnore()));
    }

    private void validateEmptyCollections() {
        List<String> fieldsToProcess = emptyCollections.stream()
            .filter(filterFieldsToIgnore(validatorConfig.getEmptyCollectionFieldsToIgnore()))
            .toList();
        assertTrue(fieldsToProcess.isEmpty(), "Empty collections found in: %s. Fields ignored: %s"
            .formatted(fieldsToProcess, validatorConfig.getEmptyCollectionFieldsToIgnore()));
    }

    private void validateNullContainingCollection() {
        List<String> fieldsToProcess = nullContainingCollection.stream()
            .filter(filterFieldsToIgnore(validatorConfig.getNullContainingCollectionFieldsToIgnore()))
            .toList();
        assertTrue(fieldsToProcess.isEmpty(), "Collections containing null found: %s. Fields ignored: %s"
            .formatted(fieldsToProcess, validatorConfig.getNullContainingCollectionFieldsToIgnore()));
    }

    private void validateEmptyMaps() {
        List<String> fieldsToProcess = emptyMaps.stream()
            .filter(filterFieldsToIgnore(validatorConfig.getEmptyMapFieldsToIgnore()))
            .toList();
        assertTrue(fieldsToProcess.isEmpty(), "Empty maps found: %s. Fields ignored: %s"
            .formatted(fieldsToProcess, validatorConfig.getEmptyMapFieldsToIgnore()));
    }

    private void validateNullContainingMaps() {
        List<String> fieldsToProcess = nullContainingMaps.stream()
            .filter(filterFieldsToIgnore(validatorConfig.getNullContainingMapFieldsToIgnore()))
            .toList();
        assertTrue(fieldsToProcess.isEmpty(), "Maps containing null found: %s. Fields ignored: %s"
            .formatted(fieldsToProcess, validatorConfig.getNullContainingMapFieldsToIgnore()));
    }

    private void validateNewItems() {
        List<String> newItemsList = newItems.entrySet().stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue())
            .toList();
        assertTrue(newItemsList.isEmpty(), "New items found: %s".formatted(newItemsList));
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
