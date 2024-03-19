package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountStates extends IdElement {
    @JsonProperty("favorite")
    private Boolean favorite;

    @JsonProperty("rated")
    private Object rated;

    @JsonProperty("watchlist")
    private Boolean watchlist;
}
