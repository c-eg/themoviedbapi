package info.movito.themoviedbapi.model.core.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Artwork extends AbstractJsonMapping {
    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("aspect_ratio")
    private Double aspectRatio;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;
}
