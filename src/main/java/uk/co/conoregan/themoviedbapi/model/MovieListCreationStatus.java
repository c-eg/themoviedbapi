package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.core.responses.ResponseStatus;

@Getter
@Setter
public class MovieListCreationStatus extends ResponseStatus {
    @JsonProperty("list_id")
    private String listId;
}
