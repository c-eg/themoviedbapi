package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCast extends Person {
    @JsonProperty("character")
    private String character;

    @JsonProperty("order")
    private Integer order;
}
