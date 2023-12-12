package uk.co.conoregan.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import org.apache.commons.lang3.StringUtils;

public class Person extends NamedIdElement {
    @JsonProperty("profile_path")
    protected String profilePath;

    @JsonProperty("cast_id")
    private int castId;

    @JsonProperty("credit_id")
    private String creditId;

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = StringUtils.trimToEmpty(profilePath);
    }

    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }
}
