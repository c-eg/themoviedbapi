package info.movito.themoviedbapi.util;

import java.util.Collection;
import java.util.List;

import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;

/**
 * TMDB API Model Utility.
 */
public final class ModelUtil {
    private ModelUtil() {
    }

    /**
     * Gets a list of names (from the name field) from the items in the collection provided.
     * For model classes extending {@link NamedIdElement}.
     *
     * @param collection the collection.
     * @return the list of names.
     */
    public static List<String> getNames(Collection<? extends NamedIdElement> collection) {
        return collection.stream().map(NamedIdElement::getName).toList();
    }

    /**
     * Gets a list of names (from the name field) from the items in the collection provided.
     * For model classes extending {@link NamedStringIdElement}.
     *
     * @param collection the collection.
     * @return the list of names.
     */
    public static List<String> getNamesString(Collection<? extends NamedStringIdElement> collection) {
        return collection.stream().map(NamedStringIdElement::getName).toList();
    }
}
