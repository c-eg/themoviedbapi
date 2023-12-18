package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import info.movito.themoviedbapi.model.core.NamedIdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends NamedIdElement {
    @JsonProperty("profile_path")
    protected String profilePath;

    @JsonProperty("cast_id")
    private Integer castId;

    @JsonProperty("credit_id")
    private String creditId;

    public void setProfilePath(String profilePath) {
        this.profilePath = StringUtils.trimToEmpty(profilePath);
    }
}
