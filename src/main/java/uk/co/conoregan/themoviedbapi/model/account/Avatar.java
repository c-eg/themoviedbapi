package uk.co.conoregan.themoviedbapi.model.account;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@Data
@EqualsAndHashCode(callSuper = false)
public class Avatar extends AbstractJsonMapping {
    @JsonProperty("gravatar")
    private Map<String, String> gravatar = new HashMap<>();

    @JsonProperty("tmdb")
    private Map<String, String> tmdb = new HashMap<>();

    public String getGravatarHash() {
        return gravatar.get("hash");
    }

    public String getTmdbAvatarPath() {
        return tmdb.get("avatar_path");
    }
}
