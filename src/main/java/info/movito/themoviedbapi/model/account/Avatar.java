package info.movito.themoviedbapi.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = false)
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
