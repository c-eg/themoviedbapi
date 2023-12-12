package uk.co.conoregan.themoviedbapi.model.certifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CertificationResults extends AbstractJsonMapping {
    @JsonProperty("certifications")
    private Map<String, List<Certification>> certifications;
}
