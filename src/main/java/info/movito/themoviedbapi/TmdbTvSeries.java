package info.movito.themoviedbapi;

import java.util.HashMap;

import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.ReviewResultsPage;
import info.movito.themoviedbapi.model.core.TvKeywords;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.tv.core.ChangeResults;
import info.movito.themoviedbapi.model.tv.core.credits.AggregateCredits;
import info.movito.themoviedbapi.model.tv.core.credits.Credits;
import info.movito.themoviedbapi.model.tv.series.AlternativeTitleResults;
import info.movito.themoviedbapi.model.tv.series.ContentRatingResults;
import info.movito.themoviedbapi.model.tv.series.EpisodeGroupResults;
import info.movito.themoviedbapi.model.tv.series.ExternalIds;
import info.movito.themoviedbapi.model.tv.series.Images;
import info.movito.themoviedbapi.model.tv.series.ScreenedTheatricallyResults;
import info.movito.themoviedbapi.model.tv.series.Translations;
import info.movito.themoviedbapi.model.tv.series.TvSeriesDb;
import info.movito.themoviedbapi.model.tv.series.TvSeriesListResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.TvSeriesAppendToResponse;
import info.movito.themoviedbapi.util.Utils;

/**
 * The movie database api for tv series. See the
 * <a href="https://developer.themoviedb.org/reference/tv-series-details">documentation</a> for more info.
 */
public class TmdbTvSeries extends AbstractTmdbApi {
    public static final String TMDB_METHOD_TV = "tv";

    /**
     * Create a new TmdbTvSeries instance to call the tv series TMDb API methods.
     */
    TmdbTvSeries(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the details of a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-details">documentation</a> for more info.</p>
     *
     * @param seriesId         The TMDb id of the tv series.
     * @param language         optional - The language to query the results in. Default: en-US.
     * @param appendToResponse optional - additional namespaces to append to the result (20 max).
     * @return The tv series details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesDb getDetails(int seriesId, String language, TvSeriesAppendToResponse... appendToResponse) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId);
        apiUrl.addLanguage(language);
        apiUrl.addAppendToResponses(appendToResponse);
        return mapJsonResult(apiUrl, TvSeriesDb.class);
    }

    /**
     * <p>Get the rating, watchlist and favourite status.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-account-states">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the tv series.
     * @param sessionId      optional - The session id of the user.
     * @param guestSessionId optional - The guest session id of the user.
     * @return The account states of the tv series.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AccountStates getAccountStates(int seriesId, String sessionId, String guestSessionId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "account_states");
        apiUrl.addQueryParam("session_id", sessionId);
        apiUrl.addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, AccountStates.class);
    }

    /**
     * <p>Get the aggregate credits (cast and crew) that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-aggregate-credits">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @param language optional - The language to query the results in. Default: en-US.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AggregateCredits getAggregateCredits(int seriesId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "aggregate_credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, AggregateCredits.class);
    }

    /**
     * <p>Get the alternative titles that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-alternative-titles">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The alternative titles of the tv series.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AlternativeTitleResults getAlternativeTitles(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "alternative_titles");
        return mapJsonResult(apiUrl, AlternativeTitleResults.class);
    }

    /**
     * <p>Get the recent changes for a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-changes">documentation</a> for more info.</p>
     *
     * @param seriesId  The TMDb id of the tv series.
     * @param startDate optional - The start date, in format: YYYY-MM-DD.
     * @param endDate   optional - The end date, in format: YYYY-MM-DD.
     * @param page      optional - The page of results to return. Default: 1.
     * @return The tv series changes.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangeResults getChanges(int seriesId, String startDate, String endDate, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "changes");
        apiUrl.addQueryParam("start_date", startDate);
        apiUrl.addQueryParam("end_date", endDate);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, ChangeResults.class);
    }

    /**
     * <p>Get the content ratings that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-content-ratings">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The tv series content ratings.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ContentRatingResults getContentRatings(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "content_ratings");
        return mapJsonResult(apiUrl, ContentRatingResults.class);
    }

    /**
     * <p>Get the latest season credits of a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-credits">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @param language optional - The language to query the results in. Default: en-US.
     * @return The tv series credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Credits getCredits(int seriesId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, Credits.class);
    }

    /**
     * <p>Get the episode groups that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-episode-groups">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The tv series episode groups.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public EpisodeGroupResults getEpisodeGroups(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "episode_groups");
        return mapJsonResult(apiUrl, EpisodeGroupResults.class);
    }

    /**
     * <p>Get a list of external IDs that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-external-ids">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The external IDs.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ExternalIds getExternalIds(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "external_ids");
        return mapJsonResult(apiUrl, ExternalIds.class);
    }

    /**
     * <p>Get the images that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-images">documentation</a> for more info.</p>
     *
     * @param seriesId             The TMDb id of the tv series.
     * @param language             optional - The language to query the results in. Default: en-US.
     * @param includeImageLanguage optional - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The tv series images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Images getImages(int seriesId, String language, String... includeImageLanguage) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "images");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParamCommandSeparated("include_image_language", includeImageLanguage);
        return mapJsonResult(apiUrl, Images.class);
    }

    /**
     * <p>Get a list of keywords that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-keywords">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The tv series keywords.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvKeywords getKeywords(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "keywords");
        return mapJsonResult(apiUrl, TvKeywords.class);
    }

    /**
     * <p>Get the newest TV show ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-latest-id">documentation</a> for more info.</p>
     *
     * @return The latest tv series.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesDb getLatest() throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, "latest");
        return mapJsonResult(apiUrl, TvSeriesDb.class);
    }

    /**
     * <p>Get the lists that a TV series has been added to..</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/lists-copy">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @param language optional - The language to query the results in. Default: en-US.
     * @param page     optional - The page of results to return. Default: 1.
     * @return The tv series lists.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesListResultsPage getLists(int seriesId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "lists");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, TvSeriesListResultsPage.class);
    }

    /**
     * <p>Get the recommendations for a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-recommendations">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @param language optional - The language to query the results in. Default: en-US.
     * @param page     optional - The page of results to return. Default: 1.
     * @return The tv series lists.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getRecommendations(int seriesId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "recommendations");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }

    /**
     * <p>Get the reviews that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-reviews">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @param language optional - The language to query the results in. Default: en-US.
     * @param page     optional - The page of results to return. Default: 1.
     * @return The tv series reviews.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ReviewResultsPage getReviews(int seriesId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "reviews");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, ReviewResultsPage.class);
    }

    /**
     * <p>Get the seasons and episodes that have screened theatrically.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-screen-credits">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The tv series seasons and episodes that have screened theatrically.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ScreenedTheatricallyResults getScreenedTheatrically(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "screened_theatrically");
        return mapJsonResult(apiUrl, ScreenedTheatricallyResults.class);
    }

    /**
     * <p>Get the similar TV shows.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-similar">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @param language optional - The language to query the results in. Default: en-US.
     * @param page     optional - The page of results to return. Default: 1.
     * @return The similar tv series.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeriesResultsPage getSimilar(int seriesId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "similar");
        apiUrl.addLanguage(language);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, TvSeriesResultsPage.class);
    }

    /**
     * <p>Get the translations that have been added to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-translations">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The translations.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Translations getTranslations(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "translations");
        return mapJsonResult(apiUrl, Translations.class);
    }

    /**
     * <p>Get the videos that belong to a TV show.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-videos">documentation</a> for more info.</p>
     *
     * @param seriesId             The TMDb id of the tv series.
     * @param language             optional - The language to query the results in. Default: en-US.
     * @param includeVideoLanguage optional - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The videos.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public VideoResults getVideos(int seriesId, String language, String... includeVideoLanguage) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "videos");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParamCommandSeparated("include_image_language", includeVideoLanguage);
        return mapJsonResult(apiUrl, VideoResults.class);
    }

    /**
     * <p>Get the list of streaming providers we have for a TV show.</p>
     * <p>In order to use this data you must attribute the source of the data as JustWatch. If TMDb find any usage not complying with these
     * terms TMDb will revoke access to the API.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-watch-providers">documentation</a> for more info.</p>
     *
     * @param seriesId The TMDb id of the tv series.
     * @return The watch providers.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ProviderResults getWatchProviders(int seriesId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "watch/providers");
        return mapJsonResult(apiUrl, ProviderResults.class);
    }

    /**
     * <p>Rate a TV show and save it to your rated list.</p>
     * <p>Note: if no <code>guestSessionId</code> or <code>sessionId</code> are provided, the method will add the rating to the API key
     * holder's account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-add-rating">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the tv series.
     * @param guestSessionId optional - The guest session id of the user.
     * @param sessionId      optional - The session id of the user.
     * @param rating         The rating of the tv series. Must be: 0 &lt; rating &le; 10.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus addRating(int seriesId, String guestSessionId, String sessionId, double rating) throws TmdbException {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be: 0 < rating <= 10");
        }

        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "rating");
        apiUrl.addQueryParam("session_id", sessionId);
        apiUrl.addQueryParam("guest_session_id", guestSessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("value", rating);

        String jsonBody = Utils.convertToJson(getObjectMapper(), body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Delete a user rating.</p>
     * <p>Note: if no <code>guestSessionId</code> or <code>sessionId</code> are provided, the method will delete the rating for the API key
     * holder's account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-series-delete-rating">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the tv series.
     * @param guestSessionId optional - The guest session id of the user.
     * @param sessionId      optional - The session id of the user.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus deleteRating(int seriesId, String guestSessionId, String sessionId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, "rating");
        apiUrl.addQueryParam("session_id", sessionId);
        apiUrl.addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, null, RequestType.DELETE, ResponseStatus.class);
    }
}
