package info.movito.themoviedbapi.model.tv.tvseries;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Job extends AbstractJsonMapping {
    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("job")
    private String job;

    @JsonProperty("episode_count")
    private Integer episodeCount;
}
