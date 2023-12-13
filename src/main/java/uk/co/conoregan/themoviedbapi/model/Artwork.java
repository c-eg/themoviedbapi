package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = true)
public class Artwork extends AbstractJsonMapping {
    @JsonProperty("iso_639_1")
    private String language;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("aspect_ratio")
    private float aspectRatio;

    @JsonProperty("height")
    private int height;

    @JsonProperty("width")
    private int width;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("flag")
    private String flag;

    private ArtworkType artworkType = ArtworkType.POSTER;
}
