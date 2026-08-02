package uk.co.conoregan.themoviedbapi.model.tv.core.credits;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.people.Gender;
import uk.co.conoregan.themoviedbapi.model.tv.series.Job;

@Data
@EqualsAndHashCode(callSuper = true)
public class AggregateCrew extends NamedIdElement {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("profile_path")
    private String profilePath;

    @JsonProperty("jobs")
    private List<Job> jobs = new ArrayList<>();

    @JsonProperty("department")
    private String department;

    @JsonProperty("total_episode_count")
    private Integer totalEpisodeCount;
}
