package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Credits extends IdElement {
    @JsonProperty("crew")
    List<PersonCrew> crew;

    @JsonProperty("cast")
    List<PersonCast> cast;

    @JsonProperty("guest_stars")
    List<PersonCast> guestStars;

    public List<PersonCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonCrew> crew) {
        this.crew = crew;
    }

    public List<PersonCast> getCast() {
        return cast;
    }

    public void setCast(List<PersonCast> cast) {
        this.cast = cast;
    }

    public List<PersonCast> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(List<PersonCast> guestStars) {
        this.guestStars = guestStars;
    }

    /**
     * Convenience wrapper to get all people involved in the movie.
     */
    public List<Person> getAll() {
        List<Person> involved = new ArrayList<>();

        involved.addAll(Optional.ofNullable(crew).orElse(new ArrayList<>()));
        involved.addAll(Optional.ofNullable(cast).orElse(new ArrayList<>()));
        involved.addAll(Optional.ofNullable(guestStars).orElse(new ArrayList<>()));

        return involved;
    }
}
