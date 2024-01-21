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
    @JsonProperty("certifications")
    private Map<String, List<Certification>> certifications;
}
