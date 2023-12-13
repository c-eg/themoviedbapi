package uk.co.conoregan.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.Credits;
import uk.co.conoregan.themoviedbapi.model.ExternalIds;
import uk.co.conoregan.themoviedbapi.model.MovieImages;
import uk.co.conoregan.themoviedbapi.model.Video;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.keywords.TvKeywords;

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
    private MovieImages images;

    @JsonProperty("videos")
    private Video.Results videos;

    @JsonProperty("keywords")
    private TvKeywords keywords;
}
