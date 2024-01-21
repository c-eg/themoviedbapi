package info.movito.themoviedbapi.model.certifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class CertificationResults extends AbstractJsonMapping {
    // TODO: this might be best wrapped around the ResultsPage class
    @JsonProperty("certifications")
    private Map<String, List<Certification>> certifications;
}
