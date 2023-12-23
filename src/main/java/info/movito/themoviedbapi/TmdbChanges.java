package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.changes.ChangesResultsPage;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.util.Utils.calculateDaysDifference;

/**
 * The movie database api for changes. See the
 * <a href="https://developer.themoviedb.org/reference/changes-movie-list">documentation</a> for more info.
 */
public class TmdbChanges extends AbstractTmdbApi {
    private static final String TMDB_METHOD_CHANGES = "changes";

    private static final String TMDB_METHOD_MOVIE = "movie";

    private static final String TMDB_METHOD_PERSON = "person";

    private static final String TMDB_METHOD_TV = "tv";

    /**
     * Create a new TmdbChanges instance to call the changes related TMDb API methods.
     */
    TmdbChanges(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get a list of all the movie ids that have been changed in the past 24 hours.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/changes-movie-list">documentation</a> for more info.</p>
     *
     * @param startDate optional - The start date, in format: YYYY-MM-DD.
     * @param endDate optional - The end date, in format: YYYY-MM-DD.
     * @param page optional - The page.
     * @return The changes results page.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangesResultsPage getMovieChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        if (calculateDaysDifference(startDate, endDate) > 14) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_MOVIE, TMDB_METHOD_CHANGES);
        apiEndpoint.addQueryParam("start_date", startDate);
        apiEndpoint.addQueryParam("end_date", endDate);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ChangesResultsPage.class);
    }

    /**
     * <p>Get a list of all the people ids that have been changed in the past 24 hours.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/changes-people-list">documentation</a> for more info.</p>
     *
     * @param startDate optional - The start date, in format: YYYY-MM-DD.
     * @param endDate optional - The end date, in format: YYYY-MM-DD.
     * @param page optional - The page.
     * @return The changes results page.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangesResultsPage getPeopleChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        if (calculateDaysDifference(startDate, endDate) > 14) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, TMDB_METHOD_CHANGES);
        apiEndpoint.addQueryParam("start_date", startDate);
        apiEndpoint.addQueryParam("end_date", endDate);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ChangesResultsPage.class);
    }

    /**
     * <p>Get a list of all the tv ids that have been changed in the past 24 hours.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/changes-tv-list">documentation</a> for more info.</p>
     *
     * @param startDate optional - The start date, in format: YYYY-MM-DD.
     * @param endDate optional - The end date, in format: YYYY-MM-DD.
     * @param page optional - The page.
     * @return optional - The changes results page.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangesResultsPage getTvChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        if (calculateDaysDifference(startDate, endDate) > 14) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, TMDB_METHOD_CHANGES);
        apiEndpoint.addQueryParam("start_date", startDate);
        apiEndpoint.addQueryParam("end_date", endDate);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ChangesResultsPage.class);
    }
}
