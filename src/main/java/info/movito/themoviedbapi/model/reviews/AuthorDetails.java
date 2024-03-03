package info.movito.themoviedbapi.model.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorDetails extends NamedElement {
    @JsonProperty("username")
    private String username;

    @JsonProperty("avatar_path")
    private String avatarPath;

    @JsonProperty("rating")
    private String rating;
}
