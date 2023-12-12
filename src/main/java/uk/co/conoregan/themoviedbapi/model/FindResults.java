package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;
import uk.co.conoregan.themoviedbapi.model.people.Person;
import uk.co.conoregan.themoviedbapi.model.tv.TvSeriesDb;

import java.util.List;

public class FindResults extends AbstractJsonMapping {
    @JsonProperty("movie_results")
    private List<MovieDb> movieResults;

    @JsonProperty("person_results")
    private List<Person> personResults;

    @JsonProperty("tv_results")
    private List<TvSeriesDb> tvResults;

    public List<MovieDb> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<MovieDb> movieResults) {
        this.movieResults = movieResults;
    }

    public List<Person> getPersonResults() {
        return personResults;
    }

    public void setPersonResults(List<Person> personResults) {
        this.personResults = personResults;
    }

    public List<TvSeriesDb> getTvResults() {
        return tvResults;
    }

    public void setTvResults(List<TvSeriesDb> tvResults) {
        this.tvResults = tvResults;
    }
}
