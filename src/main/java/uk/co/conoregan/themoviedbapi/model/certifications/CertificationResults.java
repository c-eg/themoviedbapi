package uk.co.conoregan.themoviedbapi.model.certifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class CertificationResults extends AbstractJsonMapping {
    // TODO: this might be best wrapped around the ResultsPage class
    @JsonProperty("certifications")
    private Map<String, List<Certification>> certifications;
}
