package info.movito.themoviedbapi.model.people.credits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"media_type"})
public class MovieCrew extends Person implements Crew {
    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;

    @Override
    public MediaType getMediaType() {
        return MediaType.MOVIE;
    }
}
