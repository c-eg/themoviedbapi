package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class PersonPeople extends Person {

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
    private List<String> aka = new ArrayList<String>();
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


    /**
     * Add a crew member
     *
     * @param id
     * @param name
     * @param profilePath
     * @param department
     * @param job
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
     * Add a cast member
     *
     * @param id
     * @param name
     * @param profilePath
     * @param character
     * @param order
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


    public String getCharacter() {
        return character;
    }


    public String getDepartment() {
        return department;
    }


    public String getJob() {
        return job;
    }


    public int getOrder() {
        return order;
    }


    public PersonType getPersonType() {
        return personType;
    }


    public boolean isAdult() {
        return adult;
    }


    public List<String> getAka() {
        return aka;
    }


    public String getBiography() {
        return biography;
    }


    public String getBirthday() {
        return birthday;
    }


    public String getBirthplace() {
        return birthplace;
    }


    public String getDeathday() {
        return deathday;
    }


    public String getHomepage() {
        return homepage;
    }


    public String getImdbId() {
        return imdbId;
    }


    public float getPopularity() {
        return popularity;
    }


    public void setCharacter(String character) {
        this.character = character;
    }


    public void setDepartment(String department) {
        this.department = department;
    }


    public void setJob(String job) {
        this.job = StringUtils.trimToEmpty(job);
    }


    public void setOrder(int order) {
        this.order = order;
    }


    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }


    public void setAdult(boolean adult) {
        this.adult = adult;
    }


    public void setAka(List<String> aka) {
        this.aka = aka;
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


    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }
}
