package info.movito.themoviedbapi.model.tv.season;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountState extends IdElement {
    @JsonProperty("rated")
    private Object rated;

    @JsonProperty("episode_number")
    private Integer episodeNumber;
}
