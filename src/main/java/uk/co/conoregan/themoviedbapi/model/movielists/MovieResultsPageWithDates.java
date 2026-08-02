package uk.co.conoregan.themoviedbapi.model.movielists;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.Movie;
import uk.co.conoregan.themoviedbapi.model.core.ResultsPage;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieResultsPageWithDates extends ResultsPage<Movie> {
    @JsonProperty("dates")
    private Dates dates;
}
