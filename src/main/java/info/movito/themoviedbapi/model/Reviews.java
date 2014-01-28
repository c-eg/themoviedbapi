package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.StringIdElement;


public class Reviews extends StringIdElement {


    @JsonProperty("author")
    private String author;
    @JsonProperty("content")
    private String content;
    @JsonProperty("url")
    private String url;


    public String getAuthor() {
        return author;
    }


    public String getContent() {
        return content;
    }


    public String getUrl() {
        return url;
    }
}
