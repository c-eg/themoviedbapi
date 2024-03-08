package info.movito.themoviedbapi.util;

/**
 * Fields to ignore wrapper.
 *
 * @param clazz the class.
 * @param initialFieldNameFromObjectToCheck the initial field name from the object to check.
 * @param nullFieldsToIgnore the null fields to ignore.
 */
public record FieldsToIgnore(Class<?> clazz, String initialFieldNameFromObjectToCheck, String... nullFieldsToIgnore) {

}
