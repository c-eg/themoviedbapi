package uk.co.conoregan.themoviedbapi.model.tv.season;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountState extends IdElement {
    @JsonProperty("rated")
    private Object rated;

    @JsonProperty("episode_number")
    private Integer episodeNumber;
}
