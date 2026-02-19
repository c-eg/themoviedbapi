package info.movito.themoviedbapi.model.movies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AlternativeTitle;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlternativeTitles extends IdElement {
    @JsonProperty("titles")
    private List<AlternativeTitle> titles = new ArrayList<>();
}
