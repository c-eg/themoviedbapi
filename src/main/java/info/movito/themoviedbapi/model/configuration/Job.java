package info.movito.themoviedbapi.model.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Job extends AbstractJsonMapping {
    @JsonProperty("department")
    private String department;

    @JsonProperty("jobs")
    private List<String> jobs = new ArrayList<>();
}
