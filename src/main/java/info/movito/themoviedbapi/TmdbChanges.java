package info.movito.themoviedbapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import info.movito.themoviedbapi.model.changes.ChangesResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for changes. See the
 * <a href="https://developer.themoviedb.org/reference/changes-movie-list">documentation</a> for more info.
 */
public class TmdbChanges extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_CHANGES = "changes";

    protected static final String TMDB_METHOD_MOVIE = "movie";

    protected static final String TMDB_METHOD_PERSON = "person";

    protected static final String TMDB_METHOD_TV = "tv";

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
     * @param startDate nullable - The start date, in format: YYYY-MM-DD.
     * @param endDate   nullable - The end date, in format: YYYY-MM-DD.
     * @param page      nullable - The page of results to return. Default: 1.
     * @return The changes results page.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangesResultsPage getMovieChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        if (calculateDaysDifference(startDate, endDate) > 14) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, TMDB_METHOD_CHANGES);
        apiUrl.addQueryParam("start_date", startDate);
        apiUrl.addQueryParam("end_date", endDate);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, ChangesResultsPage.class);
    }

    /**
     * <p>Get a list of all the people ids that have been changed in the past 24 hours.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/changes-people-list">documentation</a> for more info.</p>
     *
     * @param startDate nullable - The start date, in format: YYYY-MM-DD.
     * @param endDate   nullable - The end date, in format: YYYY-MM-DD.
     * @param page      nullable - The page of results to return. Default: 1.
     * @return The changes results page.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangesResultsPage getPeopleChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        if (calculateDaysDifference(startDate, endDate) > 14) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, TMDB_METHOD_CHANGES);
        apiUrl.addQueryParam("start_date", startDate);
        apiUrl.addQueryParam("end_date", endDate);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, ChangesResultsPage.class);
    }

    /**
     * <p>Get a list of all the tv ids that have been changed in the past 24 hours.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/changes-tv-list">documentation</a> for more info.</p>
     *
     * @param startDate nullable - The start date, in format: YYYY-MM-DD.
     * @param endDate   nullable - The end date, in format: YYYY-MM-DD.
     * @param page      nullable - The page of results to return. Default: 1.
     * @return nullable - The changes results page.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangesResultsPage getTvChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        if (calculateDaysDifference(startDate, endDate) > 14) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_CHANGES);
        apiUrl.addQueryParam("start_date", startDate);
        apiUrl.addQueryParam("end_date", endDate);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, ChangesResultsPage.class);
    }

    /**
     * Calculate the difference in days between two date strings.
     *
     * @param startDateString the start date string, in format: YYYY-MM-DD.
     * @param endDateString   the end date string, in format: YYYY-MM-DD.
     * @return the difference in days.
     */
    private static long calculateDaysDifference(String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
