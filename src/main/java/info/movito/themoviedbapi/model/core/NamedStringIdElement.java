package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NamedStringIdElement extends StringIdElement {


    @JsonProperty("name")
    private String name;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
