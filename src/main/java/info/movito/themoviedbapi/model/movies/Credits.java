package info.movito.themoviedbapi.model.movies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Credits extends IdElement {
    @JsonProperty("cast")
    private List<Cast> cast = new ArrayList<>();

    @JsonProperty("crew")
    private List<Crew> crew = new ArrayList<>();
}
