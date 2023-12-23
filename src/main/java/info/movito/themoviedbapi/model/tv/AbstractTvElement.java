package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.ExternalIds;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.keywords.TvKeywords;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.Image;
import info.movito.themoviedbapi.model.Video;

/**
 * Appendable responses for all tv elements.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractTvElement extends NamedIdElement {
    @JsonProperty("credits")
    private Credits credits;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @JsonProperty("images")
    private Image images;

    @JsonProperty("videos")
    private Video.Results videos;

    @JsonProperty("keywords")
    private TvKeywords keywords;
}
