package uk.co.conoregan.themoviedbapi.model.lists;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatusAuthentication;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieListCreationStatus extends ResponseStatusAuthentication {
    @JsonProperty("list_id")
    private String listId;
}
