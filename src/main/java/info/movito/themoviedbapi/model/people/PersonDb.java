package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.people.credits.CombinedPersonCredits;
import info.movito.themoviedbapi.model.people.credits.MovieCredits;
import info.movito.themoviedbapi.model.people.credits.TvCredits;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class PersonDb extends NamedIdElement implements Multi {
    @JsonProperty("adult")
    private Boolean adult;

    @JsonProperty("also_known_as")
    private List<String> alsoKnownAs;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("deathday")
    private String deathDay;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("profile_path")
    private String profilePath;

    /* append to responses */

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("combined_credits")
    private CombinedPersonCredits combinedCredits;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("changes")
    private ChangeResults changes;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("images")
    private PersonImages images;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("movie_credits")
    private MovieCredits movieCredits;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("tv_credits")
    private TvCredits tvCredits;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("translations")
    private Translations translations;

    @Override
    public MediaType getMediaType() {
        return MediaType.PERSON;
    }
}
