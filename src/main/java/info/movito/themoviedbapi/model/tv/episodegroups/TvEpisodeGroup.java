package info.movito.themoviedbapi.model.tv.episodegroups;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvEpisodeGroup extends NamedStringIdElement {
    @JsonProperty("order")
    private Integer order;

    @JsonProperty("episodes")
    private List<OrderedTvEpisode> episodes;

    @JsonProperty("locked")
    private Boolean locked;
}
