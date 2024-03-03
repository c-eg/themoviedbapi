package info.movito.themoviedbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.util.serializers.movies.RatedDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountStates extends IdElement {
    @JsonProperty("favorite")
    private Boolean favorite;

    @JsonProperty("rated")
    @JsonDeserialize(using = RatedDeserializer.class)
    private Rated rated;

    @JsonProperty("watchlist")
    private Boolean watchlist;

    public Optional<Rated> getRated() {
        return Optional.ofNullable(rated);
    }
}
