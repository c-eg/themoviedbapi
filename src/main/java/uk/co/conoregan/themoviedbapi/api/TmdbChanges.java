package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.changes.ChangesResultsPage;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

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
     * Get a list of all the movie ids that have been changed in the past 24 hours.
     *
     * @param startDate the start date, in format: YYYY-MM-DD.
     * @param endDate the end date, in format: YYYY-MM-DD.
     * @param page the page.
     * @return the changes results page.
     */
    public ChangesResultsPage getMovieChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_MOVIE, TMDB_METHOD_CHANGES);
        apiEndpoint.addQueryParam("start_date", startDate);
        apiEndpoint.addQueryParam("end_date", endDate);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ChangesResultsPage.class);
    }

    /**
     * Get a list of all the people ids that have been changed in the past 24 hours.
     *
     * @param startDate the start date, in format: YYYY-MM-DD.
     * @param endDate the end date, in format: YYYY-MM-DD.
     * @param page the page.
     * @return the changes results page.
     */
    public ChangesResultsPage getPeopleChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_PERSON, TMDB_METHOD_CHANGES);
        apiEndpoint.addQueryParam("start_date", startDate);
        apiEndpoint.addQueryParam("end_date", endDate);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ChangesResultsPage.class);
    }

    /**
     * Get a list of all the tv ids that have been changed in the past 24 hours.
     *
     * @param startDate the start date, in format: YYYY-MM-DD.
     * @param endDate the end date, in format: YYYY-MM-DD.
     * @param page the page.
     * @return the changes results page.
     */
    public ChangesResultsPage getTvChangesList(String startDate, String endDate, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, TMDB_METHOD_CHANGES);
        apiEndpoint.addQueryParam("start_date", startDate);
        apiEndpoint.addQueryParam("end_date", endDate);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ChangesResultsPage.class);
    }
}
