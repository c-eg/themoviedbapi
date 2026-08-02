package uk.co.conoregan.themoviedbapi.model.tv.episodegroups;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedStringIdElement;
import uk.co.conoregan.themoviedbapi.model.tv.core.Network;

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
    private List<TvEpisodeGroup> groups = new ArrayList<>();

    @JsonProperty("network")
    private Network network;

    @JsonProperty("type")
    private EpisodeGroupType type;
}
