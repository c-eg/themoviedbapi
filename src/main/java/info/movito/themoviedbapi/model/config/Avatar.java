package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class Avatar extends AbstractJsonMapping {
    @JsonProperty("gravatar")
    private HashMap<String, String> gravatar;

    @JsonProperty("tmdb")
    private HashMap<String, String> tmdb;

    public String getGravatarHash() {
        return gravatar.get("hash");
    }

    public String getTmdbAvatarPath() {
        return tmdb.get("avatar_path");
    }
}
