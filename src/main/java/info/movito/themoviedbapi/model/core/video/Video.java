package info.movito.themoviedbapi.model.core.video;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("video")
public class Video extends NamedStringIdElement {
    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("iso_3166_1")
    private String iso31661;

    @JsonProperty("key")
    private String key;

    @JsonProperty("site")
    private String site;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("type")
    private String type;

    @JsonProperty("official")
    private Boolean official;

    @JsonProperty("published_at")
    private String publishedAt;
}
