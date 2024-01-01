package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends NamedIdElement {
    @JsonProperty("username")
    private String userName;

    @JsonProperty("include_adult")
    private boolean includeAdult;

    @JsonProperty("avatar")
    private HashMap<String, HashMap<String, String>> avatar;

    /**
     * Gets the gravatar hash.
     *
     * @return the gravatar hash.
     */
    public String getGravatarHash() {
        if (avatar != null && avatar.get("gravatar") != null) {
            return avatar.get("gravatar").get("hash");
        }

        return null;
    }
}
