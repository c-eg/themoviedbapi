package info.movito.themoviedbapi.model.config;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.Collection;
import java.util.List;

public class Timezone extends AbstractJsonMapping {

    private String name;
    private String country;

    public Timezone(String name, String country) {
        this.name = name;
        this.country = country;
    }

	  public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
