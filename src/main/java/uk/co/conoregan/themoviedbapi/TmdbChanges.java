package uk.co.conoregan.themoviedbapi;

import uk.co.conoregan.themoviedbapi.model.changes.ChangesResultsPage;
import uk.co.conoregan.themoviedbapi.tools.ApiUrl;
import uk.co.conoregan.themoviedbapi.tools.TmdbApiClient;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.util.DateUtil;

/**
 * The movie database api for changes. See the
 * <a href="https://developer.themoviedb.org/reference/changes-movie-list">documentation</a> for more info.
 */
public class TmdbChanges {
    protected static final String TMDB_METHOD_CHANGES = "changes";

    protected static final String TMDB_METHOD_MOVIE = "movie";

    protected static final String TMDB_METHOD_PERSON = "person";

    protected static final String TMDB_METHOD_TV = "tv";

    private static final int MAX_DATE_RANGE_DAYS = 14;

    private final TmdbApiClient tmdbApiClient;

    /**
     * Create a new TmdbChanges instance to call the changes related TMDb API methods.
     */
    TmdbChanges(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
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
        if (exceedsMaxDateRange(startDate, endDate)) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, TMDB_METHOD_CHANGES)
            .addQueryParam("start_date", startDate)
            .addQueryParam("end_date", endDate)
            .addPage(page);

        return tmdbApiClient.get(apiUrl, ChangesResultsPage.class);
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
        if (exceedsMaxDateRange(startDate, endDate)) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_PERSON, TMDB_METHOD_CHANGES)
            .addQueryParam("start_date", startDate)
            .addQueryParam("end_date", endDate)
            .addPage(page);

        return tmdbApiClient.get(apiUrl, ChangesResultsPage.class);
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
        if (exceedsMaxDateRange(startDate, endDate)) {
            throw new IllegalArgumentException("The date range must be less than or equal to 14 days.");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_CHANGES)
            .addQueryParam("start_date", startDate)
            .addQueryParam("end_date", endDate)
            .addPage(page);

        return tmdbApiClient.get(apiUrl, ChangesResultsPage.class);
    }

    private static boolean exceedsMaxDateRange(String startDate, String endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }

        return DateUtil.calculateDaysDifference(startDate, endDate) > MAX_DATE_RANGE_DAYS;
    }
}
