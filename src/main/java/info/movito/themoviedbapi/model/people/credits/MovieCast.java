package info.movito.themoviedbapi.model.people.credits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"media_type"})
public class MovieCast extends Person implements Cast {
    @JsonProperty("character")
    private String character;

    @JsonProperty("order")
    private Integer order;

    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
