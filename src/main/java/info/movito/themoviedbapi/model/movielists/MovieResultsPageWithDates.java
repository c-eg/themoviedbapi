package info.movito.themoviedbapi.model.movielists;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.ResultsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieResultsPageWithDates extends ResultsPage<Movie> {
    @JsonProperty("dates")
    private Dates dates;
}
