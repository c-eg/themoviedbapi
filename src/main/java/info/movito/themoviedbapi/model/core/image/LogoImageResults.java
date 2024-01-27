package info.movito.themoviedbapi.model.core.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class LogoImageResults extends IdElement {
    @JsonProperty("logos")
    private List<LogoImage> logos;
}
