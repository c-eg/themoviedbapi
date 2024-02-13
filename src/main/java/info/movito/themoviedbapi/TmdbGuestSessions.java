package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.rated.RatedMovieResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvEpisodeResultsPage;
import info.movito.themoviedbapi.model.rated.RatedTvSeriesResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.sortby.AccountSortBy;

/**
 * The movie database api for guest sessions. See the
 * <a href="https://developer.themoviedb.org/reference/guest-session-rated-movies">documentation</a> for more info.
 */
public class TmdbGuestSessions extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_GUEST_SESSIONS = "guest_session";

    /**
     * Create a new TmdbGuestSessions instance to call the guest sessions related TMDb API methods.
     */
    public TmdbGuestSessions(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the rated movies for a guest session.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/guest-session-rated-movies">documentation</a> for more info.</p>
     *
     * @param guestSessionId The guest session id of the user.
     * @param language optional - The language to use for the results.
     * @param page optional - The page to return.
     * @param sortBy optional - The sort order of the results.
     * @return The rated movies of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RatedMovieResultsPage getRatedMovies(int guestSessionId, String language, Integer page,
                                                AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GUEST_SESSIONS, guestSessionId, "rated/movies");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, RatedMovieResultsPage.class);
    }

    /**
     * <p>Get the rated tv shows for a guest session.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/guest-session-rated-tv">documentation</a> for more info.</p>
     *
     * @param guestSessionId The guest session id of the user.
     * @param language optional - The language to use for the results.
     * @param page optional - The page to return.
     * @param sortBy optional - The sort order of the results.
     * @return The rated tv series of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RatedTvSeriesResultsPage getRatedTvSeries(int guestSessionId, String language, Integer page,
                                                     AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GUEST_SESSIONS, guestSessionId, "rated/tv");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, RatedTvSeriesResultsPage.class);
    }

    /**
     * <p>Get the rated tv episodes for a guest session.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/guest-session-rated-tv-episodes">documentation</a> for more info.</p>
     *
     * @param guestSessionId The guest session id of the user.
     * @param language optional - The language to use for the results.
     * @param page optional - The page to return.
     * @param sortBy optional - The sort order of the results.
     * @return The rated tv episodes of the user.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public RatedTvEpisodeResultsPage getRatedTvEpisodes(int guestSessionId, String language, Integer page,
                                                        AccountSortBy sortBy) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_GUEST_SESSIONS, guestSessionId, "rated/tv/episodes");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        apiUrl.addSortBy(sortBy);

        return mapJsonResult(apiUrl, RatedTvEpisodeResultsPage.class);
    }
}
