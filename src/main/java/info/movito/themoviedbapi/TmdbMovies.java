package info.movito.themoviedbapi;

import java.util.HashMap;

import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ReviewResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.movies.AlternativeTitles;
import info.movito.themoviedbapi.model.movies.Credits;
import info.movito.themoviedbapi.model.movies.ExternalIds;
import info.movito.themoviedbapi.model.movies.Images;
import info.movito.themoviedbapi.model.movies.KeywordResults;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.movies.ReleaseDateResults;
import info.movito.themoviedbapi.model.movies.Translations;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.MovieAppendToResponse;
import info.movito.themoviedbapi.util.JsonUtil;

/**
 * The movie database api for movies. See the
 * <a href="https://developer.themoviedb.org/reference/movie-details">documentation</a> for more info.
 */
public class TmdbMovies extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_MOVIE = "movie";

    /**
     * Create a new TmdbMovies instance to call the movie related TMDb API methods.
     */
    public TmdbMovies(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the top level details of a movie by ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-details">documentation</a> for more info.</p>
     *
     * @param movieId          The TMDb id of the movie.
     * @param language         nullable - The language to query the results in. Default: en-US.
     * @param appendToResponse nullable - additional namespaces to append to the result (20 max).
     * @return The movie details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieDb getDetails(int movieId, String language, MovieAppendToResponse... appendToResponse) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId)
            .addLanguage(language)
            .addAppendToResponses(appendToResponse);
        return mapJsonResult(apiUrl, MovieDb.class);
    }

    /**
     * <p>Get the rating, watchlist and favourite status of an account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-account-states">documentation</a> for more info.</p>
     *
     * @param movieId        The TMDb id of the movie.
     * @param sessionId      nullable - The session id of the user.
     * @param guestSessionId nullable - The guest session id of the user.
     * @return The account states of the movie.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AccountStates getAccountStates(int movieId, String sessionId, String guestSessionId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "account_states")
            .addQueryParam("session_id", sessionId)
            .addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, AccountStates.class);
    }

    /**
     * <p>Get the alternative titles for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-alternative-titles">documentation</a> for more info.</p>
     *
     * @param movieId The TMDb id of the movie.
     * @param country nullable - The country to query the results in (ISO-3166-1), e.g. "US".
     * @return The alternative titles of the movie.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AlternativeTitles getAlternativeTitles(int movieId, String country) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "alternative_titles")
            .addQueryParam("country", country);
        return mapJsonResult(apiUrl, AlternativeTitles.class);
    }

    /**
     * <p>Get the recent changes for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-changes">documentation</a> for more info.</p>
     *
     * @param movieId   The TMDb id of the movie.
     * @param startDate nullable - The start date, in format: YYYY-MM-DD.
     * @param endDate   nullable - The end date, in format: YYYY-MM-DD.
     * @param page      nullable - The page of results to return. Default: 1.
     * @return The movie changes.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangeResults getChanges(int movieId, String startDate, String endDate, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "changes")
            .addQueryParam("start_date", startDate)
            .addQueryParam("end_date", endDate)
            .addPage(page);
        return mapJsonResult(apiUrl, ChangeResults.class);
    }

    /**
     * <p>Get the credits for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-credits">documentation</a> for more info.</p>
     *
     * @param movieId  The TMDb id of the movie.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @return The movie credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Credits getCredits(int movieId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "credits")
            .addLanguage(language);
        return mapJsonResult(apiUrl, Credits.class);
    }

    /**
     * <p>Get the external ID's for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-external-ids">documentation</a> for more info.</p>
     *
     * @param movieId The TMDb id of the movie.
     * @return The external IDs.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ExternalIds getExternalIds(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "external_ids");
        return mapJsonResult(apiUrl, ExternalIds.class);
    }

    /**
     * <p>Get the images that belong to a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-images">documentation</a> for more info.</p>
     *
     * @param movieId              The movie id.
     * @param language             nullable - The language to query the results in. Default: en-US.
     * @param includeImageLanguage nullable - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Images getImages(int movieId, String language, String... includeImageLanguage) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "images")
            .addLanguage(language)
            .addQueryParamCommandSeparated("include_image_language", includeImageLanguage);
        return mapJsonResult(apiUrl, Images.class);
    }

    /**
     * <p>Get the keywords that belong to a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-keywords">documentation</a> for more info.</p>
     *
     * @param movieId The movie id.
     * @return The keywords.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public KeywordResults getKeywords(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "keywords");
        return mapJsonResult(apiUrl, KeywordResults.class);
    }

    /**
     * <p>Get the newest movie ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-latest-id">documentation</a> for more info.</p>
     *
     * @return The latest movie.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieDb getLatest() throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, "latest");
        return mapJsonResult(apiUrl, MovieDb.class);
    }

    /**
     * <p>Get the lists that a movie has been added to.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-lists">documentation</a> for more info.</p>
     *
     * @param movieId  The TMDb id of the movie.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return The movie lists.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieListResultsPage getLists(int movieId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "lists")
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, MovieListResultsPage.class);
    }

    /**
     * <p>Get the recommendations for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-recommendations">documentation</a> for more info.</p>
     *
     * @param movieId  The TMDb id of the movie.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return The recommended movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getRecommendations(int movieId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "recommendations")
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get the release dates and certifications for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-release-dates">documentation</a> for more info.</p>
     *
     * @param movieId The TMDb id of the movie.
     * @return The release dates and certifications.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ReleaseDateResults getReleaseDates(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "release_dates");
        return mapJsonResult(apiUrl, ReleaseDateResults.class);
    }

    /**
     * <p>Get the user reviews for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-reviews">documentation</a> for more info.</p>
     *
     * @param movieId  The TMDb id of the movie.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return The movie reviews.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ReviewResultsPage getReviews(int movieId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "reviews")
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, ReviewResultsPage.class);
    }

    /**
     * <p>Get the similar movies based on genres and keywords.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-similar">documentation</a> for more info.</p>
     *
     * @param movieId  The TMDb id of the movie.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @param page     nullable - The page of results to return. Default: 1.
     * @return The similar movies.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public MovieResultsPage getSimilar(int movieId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "similar")
            .addLanguage(language)
            .addPage(page);
        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * <p>Get the translations for a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-translations">documentation</a> for more info.</p>
     *
     * @param movieId The TMDb id of the movie.
     * @return The translations.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Translations getTranslations(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "translations");
        return mapJsonResult(apiUrl, Translations.class);
    }

    /**
     * <p>Get the videos that have been added to a movie.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-videos">documentation</a> for more info.</p>
     *
     * @param movieId  The TMDb id of the movie.
     * @param language nullable - The language to query the results in. Default: en-US.
     * @return The videos.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public VideoResults getVideos(int movieId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "videos")
            .addLanguage(language);
        return mapJsonResult(apiUrl, VideoResults.class);
    }

    /**
     * <p>Get the list of streaming providers we have for a movie.</p>
     * <p>In order to use this data you must attribute the source of the data as JustWatch. If TMDb find any usage not complying with these
     * terms TMDb will revoke access to the API.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-watch-providers">documentation</a> for more info.</p>
     *
     * @param movieId The TMDb id of the movie.
     * @return The watch providers.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ProviderResults getWatchProviders(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "watch/providers");
        return mapJsonResult(apiUrl, ProviderResults.class);
    }

    /**
     * <p>Rate a movie and save it to your rated list.</p>
     * <p>Note: if no <code>guestSessionId</code> or <code>sessionId</code> are provided, the method will add the rating to the API key
     * holder's account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-add-rating">documentation</a> for more info.</p>
     *
     * @param movieId        The TMDb id of the movie.
     * @param guestSessionId nullable - The guest session id of the user.
     * @param sessionId      nullable - The session id of the user.
     * @param rating         The rating of the movie. Must be: 0 &lt; rating &le; 10.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus addRating(int movieId, String guestSessionId, String sessionId, double rating) throws TmdbException {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be: 0 < rating <= 10");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "rating")
            .addQueryParam("session_id", sessionId)
            .addQueryParam("guest_session_id", guestSessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("value", rating);

        String jsonBody = JsonUtil.toJson(body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Delete a user rating.</p>
     * <p>Note: if no <code>guestSessionId</code> or <code>sessionId</code> are provided, the method will delete the rating for the API key
     * holder's account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/movie-delete-rating">documentation</a> for more info.</p>
     *
     * @param movieId        The TMDb id of the movie.
     * @param guestSessionId nullable - The guest session id of the user.
     * @param sessionId      nullable - The session id of the user.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus deleteRating(int movieId, String guestSessionId, String sessionId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "rating")
            .addQueryParam("session_id", sessionId)
            .addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, null, RequestType.DELETE, ResponseStatus.class);
    }
}
