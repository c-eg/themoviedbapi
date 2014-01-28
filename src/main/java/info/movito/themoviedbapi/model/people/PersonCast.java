package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;


public class PersonCast extends Person {


    @JsonProperty("character")
    private String character;

    @JsonProperty("order")
    private int order;


    public String getCharacter() {
        return character;
    }


    public int getOrder() {
        return order;
    }


    public String getProfilePath() {
        return profilePath;
    }


    public void setCharacter(String character) {
        this.character = StringUtils.trimToEmpty(character);
    }


    public void setOrder(int order) {
        this.order = order;
    }


    public void setProfilePath(String profilePath) {
        this.profilePath = StringUtils.trimToEmpty(profilePath);
    }
}
