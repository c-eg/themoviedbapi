package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.Artwork;
import uk.co.conoregan.themoviedbapi.model.ArtworkType;
import uk.co.conoregan.themoviedbapi.model.MovieImages;
import uk.co.conoregan.themoviedbapi.model.core.ResultsPage;
import uk.co.conoregan.themoviedbapi.model.people.Person;
import uk.co.conoregan.themoviedbapi.model.people.PersonCredits;
import uk.co.conoregan.themoviedbapi.model.people.PersonPeople;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.MovieDbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import java.util.List;

/**
 * The movie database api for people. See the
 * <a href="https://developer.themoviedb.org/reference/person-details">documentation</a> for more info.
 */
public class TmdbPeople extends AbstractTmdbApi {
    public static final String TMDB_METHOD_PERSON = "person";

    /**
     * Create a new TmdbPeople instance to call the people related TMDb API methods.
     */
    TmdbPeople(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * This method is used to retrieve all the basic person information.
     *
     * It will return the single highest rated profile image.
     */
    public PersonPeople getPersonInfo(int personId, String... appendToResponse) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, personId);
        apiEndpoint.appendToResponse(appendToResponse);

        String responeBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responeBody, PersonPeople.class);
    }

    /**
     * This method is used to retrieve all of the cast &amp; crew information for the person.
     *
     * It will return the single highest rated poster for each movie record.
     */
    public PersonCredits getCombinedPersonCredits(int personId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, personId, "combined_credits");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, PersonCredits.class);
    }

    /**
     * This method is used to retrieve all of the profile images for a person.
     */
    public List<Artwork> getPersonImages(int personId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, personId, "images");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieImages.class).getAll(ArtworkType.PROFILE);
    }

    /**
     * Get the changes for a specific person id.
     *
     * Changes are grouped by key, and ordered by date in descending order.
     *
     * By default, only the last 24 hours of changes are returned.
     *
     * The maximum number of days that can be returned in a single request is 14.
     *
     * The language is present on fields that are translatable.
     */
    public void getPersonChanges(int personId, String startDate, String endDate) {
        throw new MovieDbException("Not implemented yet");
    }

    /**
     * Get the list of popular people on The Movie Database.
     *
     * This list refreshes every day.
     */
    public PersonResultsPage getPersonPopular(Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, "popular");
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, PersonResultsPage.class);
    }

    /**
     * Get the latest person id.
     */
    public PersonPeople getPersonLatest() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, "latest");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, PersonPeople.class);
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
    public static class PersonResultsPage extends ResultsPage<Person> {

    }
}
