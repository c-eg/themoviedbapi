package info.movito.themoviedbapi.model.certifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CertificationResults extends AbstractJsonMapping {
    @JsonProperty("certifications")
    private Map<String, List<Certification>> certifications = new HashMap<>();
}
