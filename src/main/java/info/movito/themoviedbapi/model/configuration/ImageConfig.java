package info.movito.themoviedbapi.model.configuration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = false)
public class ImageConfig extends AbstractJsonMapping {
    @JsonProperty("base_url")
    private String baseUrl;

    @JsonProperty("secure_base_url")
    private String secureBaseUrl;

    @JsonProperty("poster_sizes")
    private List<String> posterSizes;

    @JsonProperty("backdrop_sizes")
    private List<String> backdropSizes;

    @JsonProperty("profile_sizes")
    private List<String> profileSizes;

    @JsonProperty("logo_sizes")
    private List<String> logoSizes;

    @JsonProperty("still_sizes")
    private List<String> stillSizes;

    /**
     * Check that the poster size is valid.
     */
    public boolean isValidPosterSize(String posterSize) {
        if (StringUtils.isBlank(posterSize)) {
            return false;
        }
        return posterSizes.contains(posterSize);
    }

    /**
     * Check that the backdrop size is valid.
     */
    public boolean isValidBackdropSize(String backdropSize) {
        if (StringUtils.isBlank(backdropSize)) {
            return false;
        }
        return backdropSizes.contains(backdropSize);
    }

    /**
     * Check that the profile size is valid.
     */
    public boolean isValidProfileSize(String profileSize) {
        if (StringUtils.isBlank(profileSize)) {
            return false;
        }
        return profileSizes.contains(profileSize);
    }

    /**
     * Check that the logo size is valid.
     */
    public boolean isValidLogoSize(String logoSize) {
        if (StringUtils.isBlank(logoSize)) {
            return false;
        }
        return logoSizes.contains(logoSize);
    }

    /**
     * Check to see if the size is valid for any of the images types.
     */
    public boolean isValidSize(String sizeToCheck) {
        return (isValidPosterSize(sizeToCheck)
            || isValidBackdropSize(sizeToCheck)
            || isValidProfileSize(sizeToCheck)
            || isValidLogoSize(sizeToCheck));
    }
}
