package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("video")
public class Video extends NamedStringIdElement {
    @JsonProperty("site")
    private String site;

    @JsonProperty("key")
    private String key;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("type")
    private String type;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Results extends IdElement {
        @JsonProperty("results")
        private List<Video> videos;
    }
}
