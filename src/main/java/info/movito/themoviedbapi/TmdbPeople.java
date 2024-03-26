package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.people.ExternalIds;
import info.movito.themoviedbapi.model.people.PersonDb;
import info.movito.themoviedbapi.model.people.PersonImages;
import info.movito.themoviedbapi.model.people.Translations;
import info.movito.themoviedbapi.model.people.credits.CombinedPersonCredits;
import info.movito.themoviedbapi.model.people.credits.MovieCredits;
import info.movito.themoviedbapi.model.people.credits.TvCredits;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.PersonAppendToResponse;

/**
 * The movie database api for people. See the
 * <a href="https://developer.themoviedb.org/reference/person-details">documentation</a> for more info.
 */
public class TmdbPeople extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_PERSON = "person";

    /**
     * Create a new TmdbPeople instance to call the people related TMDb API methods.
     */
    TmdbPeople(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Query the top level details of a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-details">documentation</a> for more info.</p>
     *
     * @param personId         The TMDb id of the person.
     * @param language         nullable - The language to query the results in. Default: en-US.
     * @param appendToResponse nullable - additional namespaces to append to the result (20 max).
     * @return The person details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PersonDb getDetails(int personId, String language, PersonAppendToResponse... appendToResponse) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId);
        apiUrl.addLanguage(language);
        apiUrl.addAppendToResponses(appendToResponse);
        return mapJsonResult(apiUrl, PersonDb.class);
    }

    /**
     * <p>Get the recent changes for a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-changes">documentation</a> for more info.</p>
     *
     * @param personId  The TMDb id of the person.
     * @param startDate nullable - The start date, in format: YYYY-MM-DD.
     * @param endDate   nullable - The end date, in format: YYYY-MM-DD.
     * @param page      nullable - The page of results to return. Default: 1.
     * @return The person changes.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangeResults getChanges(int personId, String startDate, String endDate, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "changes");
        apiUrl.addQueryParam("start_date", startDate);
        apiUrl.addQueryParam("end_date", endDate);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, ChangeResults.class);
    }

    /**
     * <p>Get the combined movie and TV credits that belong to a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-combined-credits">documentation</a> for more info.</p>
     *
     * @param personId The TMDb id of the person.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @return The combined person credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public CombinedPersonCredits getCombinedCredits(int personId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "combined_credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, CombinedPersonCredits.class);
    }

    /**
     * <p>Get the external ID's that belong to a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-external-ids">documentation</a> for more info.</p>
     *
     * @param personId The TMDb id of the person.
     * @return The external IDs.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ExternalIds getExternalIds(int personId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "external_ids");
        return mapJsonResult(apiUrl, ExternalIds.class);
    }

    /**
     * <p>Get the profile images that belong to a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-images">documentation</a> for more info.</p>
     *
     * @param personId The TMDb id of the person.
     * @return The profile images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PersonImages getImages(int personId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "images");
        return mapJsonResult(apiUrl, PersonImages.class);
    }

    /**
     * <p>Get the newest created person. This is a live response and will continuously change.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-latest-id">documentation</a> for more info.</p>
     *
     * @return The latest person.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public PersonDb getLatest() throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, "latest");
        return mapJsonResult(apiUrl, PersonDb.class);
    }

    /**
     * <p>Get the movie credits for a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-movie-credits">documentation</a> for more info.</p>
     *
     * @param personId The TMDb id of the person.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @return The movie credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieCredits getMovieCredits(int personId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "movie_credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, MovieCredits.class);
    }

    /**
     * <p>Get the TV credits that belong to a person..</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/person-tv-credits">documentation</a> for more info.</p>
     *
     * @param personId The TMDb id of the person.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @return The TV credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvCredits getTvCredits(int personId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "tv_credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, TvCredits.class);
    }

    /**
     * <p>Get the translations that belong to a person.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/translations">documentation</a> for more info.</p>
     *
     * @param personId The TMDb id of the person.
     * @return The translations.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Translations getTranslations(int personId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, personId, "translations");
        return mapJsonResult(apiUrl, Translations.class);
    }
}
