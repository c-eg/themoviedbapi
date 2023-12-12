package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

import java.util.List;

public class JobDepartment extends AbstractJsonMapping {
    @JsonProperty("department")
    private String department;

    @JsonProperty("jobs")
    private List<String> jobs;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }
}
