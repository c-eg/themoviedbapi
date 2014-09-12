package info.movito.themoviedbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.JobDepartment;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.tools.ApiUrl;

import java.util.List;


public class TmdbJobs extends AbstractTmdbApi {

    public static final String TMDB_METHOD_JOB = "job";


    TmdbJobs(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public List<JobDepartment> getJobs() {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_JOB, "list");

        return mapJsonResult(apiUrl, JobDepartmentResults.class).jobs;
    }


    private static class JobDepartmentResults extends AbstractJsonMapping {

        @JsonProperty("jobs")
        private List<JobDepartment> jobs;
    }
}
