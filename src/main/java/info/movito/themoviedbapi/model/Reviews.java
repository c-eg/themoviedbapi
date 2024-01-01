package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.StringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Reviews extends StringIdElement {
    @JsonProperty("author")
    private String author;

    @JsonProperty("content")
    private String content;

    @JsonProperty("url")
    private String url;
}
