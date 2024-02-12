package info.movito.themoviedbapi.model.core.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.StringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Image extends StringIdElement {
    @JsonProperty("aspect_ratio")
    private Double aspectRatio;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("file_type")
    private String fileType;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("width")
    private Integer width;
}
