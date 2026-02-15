package info.movito.themoviedbapi.testutil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
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
        validateNullContainingCollections();
        validateEmptyMaps();
        validateNullContainingMaps();
        validateNewItems();
    }

    private void validateFields(List<String> fields, List<String> fieldsToIgnore, String errorMessage) {
        List<String> offendingFields = fields.stream()
            .filter(value -> !fields.contains(value))
            .toList();
        assertTrue(offendingFields.isEmpty(), errorMessage.formatted(offendingFields, fieldsToIgnore));
    }

    private void validateNullFields() {
        validateFields(
            nullFields,
            validatorConfig.getNullFieldsToIgnore(),
            "Null fields found: %s. Fields ignored: %s"
        );
    }

    private void validateEmptyCollections() {
        validateFields(
            emptyCollections,
            validatorConfig.getEmptyCollectionFieldsToIgnore(),
            "Empty collections found in: %s. Fields ignored: %s"
        );
    }

    private void validateNullContainingCollections() {
        validateFields(
            nullContainingCollection,
            validatorConfig.getNullContainingCollectionFieldsToIgnore(),
            "Collections containing null found: %s. Fields ignored: %s"
        );
    }

    private void validateEmptyMaps() {
        validateFields(
            emptyMaps,
            validatorConfig.getEmptyMapFieldsToIgnore(),
            "Empty maps found: %s. Fields ignored: %s"
        );
    }

    private void validateNullContainingMaps() {
        validateFields(
            nullContainingMaps,
            validatorConfig.getNullContainingMapFieldsToIgnore(),
            "Maps containing null found: %s. Fields ignored: %s"
        );
    }

    private void validateNewItems() {
        List<String> newItemsList = newItems.entrySet().stream()
            .map(entry -> "%s: %s".formatted(entry.getKey(), entry.getValue()))
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
