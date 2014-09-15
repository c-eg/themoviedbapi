package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NamedStringIdElement extends StringIdElement {


    @JsonProperty("name")
    private String name;

    @JsonProperty("iso_639_1")
    private String iso639;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getIso639() {
        return iso639;
    }


    public void setIso639(String iso639) {
        this.iso639 = iso639;
    }

}
