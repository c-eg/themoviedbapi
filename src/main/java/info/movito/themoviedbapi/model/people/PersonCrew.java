package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCrew extends Person {
    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;
}
