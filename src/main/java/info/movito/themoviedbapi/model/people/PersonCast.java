package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCast extends Person {
    @JsonProperty("character")
    private String character;

    @JsonProperty("order")
    private int order;

    public void setCharacter(String character) {
        this.character = StringUtils.trimToEmpty(character);
    }
}
