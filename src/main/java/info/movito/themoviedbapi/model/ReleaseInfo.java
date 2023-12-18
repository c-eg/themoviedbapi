package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReleaseInfo extends AbstractJsonMapping {
    @JsonProperty("iso_3166_1")
    private String country;

    @JsonProperty("release_dates")
    private List<ReleaseDate> releaseDates;
}
