package info.movito.themoviedbapi.model.tv.episodegroups;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.tv.core.TvEpisode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderedTvEpisode extends TvEpisode {
    @JsonProperty("order")
    private Integer order;
}
