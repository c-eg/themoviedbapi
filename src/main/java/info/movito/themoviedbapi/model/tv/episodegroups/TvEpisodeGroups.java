package info.movito.themoviedbapi.model.tv.episodegroups;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;
import info.movito.themoviedbapi.model.tv.core.Network;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvEpisodeGroups extends NamedStringIdElement {
    @JsonProperty("description")
    private String description;

    @JsonProperty("episode_count")
    private Integer episodeCount;

    @JsonProperty("group_count")
    private Integer groupCount;

    @JsonProperty("groups")
    private List<TvEpisodeGroup> groups;

    @JsonProperty("network")
    private Network network;

    @JsonProperty("type")
    private EpisodeGroupType type;
}
