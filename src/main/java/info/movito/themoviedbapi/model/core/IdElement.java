package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * Base class for json wrappers with id element
 *
 * @author Holger Brandl
 */
public class IdElement extends AbstractJsonMapping implements Serializable {

    @JsonProperty("id")
    private int id;


    public int getId() {
        return id;
    }


    public void setId(int id) {


        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdElement idElement = (IdElement) o;

        if (id != idElement.id) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return id;
    }
}
