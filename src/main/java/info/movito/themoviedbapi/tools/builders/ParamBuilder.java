package info.movito.themoviedbapi.tools.builders;

import java.util.Map;

/**
 * Interface for a parameter builder.
 * This allows you to just add the search components you are concerned with.
 */
public interface ParamBuilder {
    /**
     * Returns the parameter map.
     *
     * @return the parameter map.
     */
    Map<String, String> getParameterMap();
}
