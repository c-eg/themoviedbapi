package uk.co.conoregan.themoviedbapi.model.tv.series;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

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
