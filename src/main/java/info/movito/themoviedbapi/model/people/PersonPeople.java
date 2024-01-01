package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.model.Multi;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class PersonPeople extends Person implements Multi {
    // todo initializers should all go away

    /*
     * Static fields for default cast information
     */
    private static final String CAST_DEPARTMENT = "acting";

    private static final String CAST_JOB = "actor";

    private static final String DEFAULT_STRING = "";

    private PersonType personType = PersonType.PERSON;

    private String department;  // Crew

    private String job;         // Crew

    private String character;   // Cast

    private int order = -1;                      // Cast

    @JsonProperty("adult")
    private boolean adult = false;  // PersonPeople info

    @JsonProperty("also_known_as")
    private List<String> aka = new ArrayList<>();

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("deathday")
    private String deathday;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("place_of_birth")
    private String birthplace;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("popularity")
    private float popularity = 0.0f;

    @JsonProperty("gender")
    private int gender;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    /**
     * Add a crew member.
     */
    public void addCrew(int id, String name, String profilePath, String department, String job) {
        setPersonType(PersonType.CREW);
        setId(id);
        setName(name);
        setProfilePath(profilePath);
        setDepartment(department);
        setJob(job);
        setCharacter("");
        setOrder(-1);
    }

    /**
     * Add a cast member.
     */
    public void addCast(int id, String name, String profilePath, String character, int order) {
        setPersonType(PersonType.CAST);
        setId(id);
        setName(name);
        setProfilePath(profilePath);
        setCharacter(character);
        setOrder(order);
        setDepartment(CAST_DEPARTMENT);
        setJob(CAST_JOB);
    }

    public void setJob(String job) {
        this.job = StringUtils.trimToEmpty(job);
    }

    public void setBiography(String biography) {
        this.biography = StringUtils.trimToEmpty(biography);
    }

    public void setBirthday(String birthday) {
        this.birthday = StringUtils.trimToEmpty(birthday);
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = StringUtils.trimToEmpty(birthplace);
    }

    public void setDeathday(String deathday) {
        this.deathday = StringUtils.trimToEmpty(deathday);
    }

    public void setHomepage(String homepage) {
        this.homepage = StringUtils.trimToEmpty(homepage);
    }

    public void setImdbId(String imdbId) {
        this.imdbId = StringUtils.trimToEmpty(imdbId);
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.PERSON;
    }
}
