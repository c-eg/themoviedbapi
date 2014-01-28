package info.movito.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class TmdbConfiguration extends AbstractJsonMapping {


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


    public List<String> getBackdropSizes() {
        return backdropSizes;
    }


    public String getBaseUrl() {
        return baseUrl;
    }


    public List<String> getPosterSizes() {
        return posterSizes;
    }


    public List<String> getProfileSizes() {
        return profileSizes;
    }


    public List<String> getLogoSizes() {
        return logoSizes;
    }


    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }


    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }


    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }


    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }


    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }


    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }


    /**
     * Copy the data from the passed object to this one
     *
     * @param config
     */
    public void clone(TmdbConfiguration config) {
        backdropSizes = config.getBackdropSizes();
        baseUrl = config.getBaseUrl();
        posterSizes = config.getPosterSizes();
        profileSizes = config.getProfileSizes();
        logoSizes = config.getLogoSizes();
    }


    /**
     * Check that the poster size is valid
     *
     * @param posterSize
     */
    public boolean isValidPosterSize(String posterSize) {
        if (StringUtils.isBlank(posterSize) || posterSizes.isEmpty()) {
            return false;
        }
        return posterSizes.contains(posterSize);
    }


    /**
     * Check that the backdrop size is valid
     *
     * @param backdropSize
     */
    public boolean isValidBackdropSize(String backdropSize) {
        if (StringUtils.isBlank(backdropSize) || backdropSizes.isEmpty()) {
            return false;
        }
        return backdropSizes.contains(backdropSize);
    }


    /**
     * Check that the profile size is valid
     *
     * @param profileSize
     */
    public boolean isValidProfileSize(String profileSize) {
        if (StringUtils.isBlank(profileSize) || profileSizes.isEmpty()) {
            return false;
        }
        return profileSizes.contains(profileSize);
    }


    /**
     * Check that the logo size is valid
     *
     * @param logoSize
     */
    public boolean isValidLogoSize(String logoSize) {
        if (StringUtils.isBlank(logoSize) || logoSizes.isEmpty()) {
            return false;
        }
        return logoSizes.contains(logoSize);
    }


    /**
     * Check to see if the size is valid for any of the images types
     *
     * @param sizeToCheck
     */
    public boolean isValidSize(String sizeToCheck) {
        return (isValidPosterSize(sizeToCheck)
                || isValidBackdropSize(sizeToCheck)
                || isValidProfileSize(sizeToCheck)
                || isValidLogoSize(sizeToCheck));
    }

}
