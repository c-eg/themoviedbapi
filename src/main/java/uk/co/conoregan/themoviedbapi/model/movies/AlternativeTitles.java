package uk.co.conoregan.themoviedbapi.model.movies;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AlternativeTitle;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlternativeTitles extends IdElement {
    @JsonProperty("titles")
    private List<AlternativeTitle> titles = new ArrayList<>();
}
