package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class JobDepartment extends AbstractJsonMapping {
    @JsonProperty("department")
    private String department;

    @JsonProperty("jobs")
    private List<String> jobs;
}
