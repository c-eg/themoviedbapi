package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonCrew extends Person {
    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;

    public void setDepartment(String department) {
        this.department = StringUtils.trimToEmpty(department);
    }

    public void setJob(String job) {
        this.job = StringUtils.trimToEmpty(job);
    }
}
