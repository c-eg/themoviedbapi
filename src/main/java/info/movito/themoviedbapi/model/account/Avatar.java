package info.movito.themoviedbapi.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class Avatar extends AbstractJsonMapping {
    @JsonProperty("gravatar")
    private Map<String, String> gravatar;

    @JsonProperty("tmdb")
    private Map<String, String> tmdb;

    public String getGravatarHash() {
        return gravatar.get("hash");
    }

    public String getTmdbAvatarPath() {
        return tmdb.get("avatar_path");
    }
}
