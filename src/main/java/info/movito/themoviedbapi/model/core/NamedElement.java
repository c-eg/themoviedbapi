package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NamedElement extends AbstractJsonMapping {

    @JsonProperty("name")
    private String name;


    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamedElement that = (NamedElement) o;

        if (!name.equals(that.name)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
