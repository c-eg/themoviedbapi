package info.movito.themoviedbapi.model.config;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.Collection;
import java.util.List;

public class Timezone extends AbstractJsonMapping {

    private String name;
    private List<String> countries;


    public Timezone(String name, List<String> countries) {
        this.name = name;
        this.countries = countries;
    }


    public Collection<String> getCountries() {
        return countries;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
