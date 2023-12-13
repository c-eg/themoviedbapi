package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;
import uk.co.conoregan.themoviedbapi.model.people.Person;
import uk.co.conoregan.themoviedbapi.model.tv.TvSeriesDb;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindResults extends AbstractJsonMapping {
    @JsonProperty("movie_results")
    private List<MovieDb> movieResults;

    @JsonProperty("person_results")
    private List<Person> personResults;

    @JsonProperty("tv_results")
    private List<TvSeriesDb> tvResults;
}
