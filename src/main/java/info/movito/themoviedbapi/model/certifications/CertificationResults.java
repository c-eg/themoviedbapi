package info.movito.themoviedbapi.model.certifications;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class CertificationResults {
    @JsonProperty("certifications")
    private Map<String, List<Certification>> certifications;

    /**
     * Get the certifications for each country.
     *
     * @return key is country code, value is list of certifications.
     */
    public Map<String, List<Certification>> getCertifications() {
        return certifications;
    }

    public void setCertifications(
        Map<String, List<Certification>> certifications) {
        this.certifications = certifications;
    }
}
