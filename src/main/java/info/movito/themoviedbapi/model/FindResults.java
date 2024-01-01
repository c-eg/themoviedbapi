package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.tv.TvSeries;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FindResults extends AbstractJsonMapping {
    @JsonProperty("movie_results")
    private List<MovieDb> movieResults;

    @JsonProperty("person_results")
    private List<Person> personResults;

    @JsonProperty("tv_results")
    private List<TvSeries> tvResults;
}
