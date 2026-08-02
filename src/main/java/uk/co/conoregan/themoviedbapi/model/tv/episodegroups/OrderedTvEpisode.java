package uk.co.conoregan.themoviedbapi.model.tv.episodegroups;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.tv.core.TvEpisode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderedTvEpisode extends TvEpisode {
    @JsonProperty("order")
    private Integer order;
}
