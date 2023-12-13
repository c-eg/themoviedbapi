package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;
import uk.co.conoregan.themoviedbapi.model.core.NamedStringIdElement;

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

    /**
     * TODO: Maybe use ResultsPage class instead of this class. and put it in it's own file.
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Results extends IdElement {
        @JsonProperty("results")
        private List<Video> videos;
    }
}
