package info.movito.themoviedbapi.model.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorDetails extends AbstractJsonMapping {
    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("avatar_path")
    private String avatarPath;

    @JsonProperty("rating")
    private Integer rating;
}
