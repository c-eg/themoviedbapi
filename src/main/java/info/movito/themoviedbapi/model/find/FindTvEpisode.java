package info.movito.themoviedbapi.model.find;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.tv.core.TvEpisode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindTvEpisode extends TvEpisode {
    @JsonProperty("episode_type")
    private String episodeType;
}
