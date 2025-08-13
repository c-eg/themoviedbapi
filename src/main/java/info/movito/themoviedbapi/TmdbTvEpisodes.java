package info.movito.themoviedbapi;

import java.util.HashMap;

import info.movito.themoviedbapi.model.core.AccountStates;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.tv.core.ChangeResults;
import info.movito.themoviedbapi.model.tv.core.Translations;
import info.movito.themoviedbapi.model.tv.episode.EpisodeCredits;
import info.movito.themoviedbapi.model.tv.episode.ExternalIds;
import info.movito.themoviedbapi.model.tv.episode.Images;
import info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.TvEpisodesAppendToResponse;
import info.movito.themoviedbapi.util.JsonUtil;

import static info.movito.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;
import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;

/**
 * The movie database api for tv episodes. See the
 * <a href="https://developer.themoviedb.org/reference/tv-episode-details">documentation</a> for more info.
 */
public class TmdbTvEpisodes extends AbstractTmdbApi {
    public static final String TMDB_METHOD_TV_EPISODE = "episode";

    /**
     * Create a new TmdbTvEpisodes instance to call the tv episodes TMDb API methods.
     */
    TmdbTvEpisodes(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Query the details of a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-details">documentation</a> for more info.</p>
     *
     * @param seriesId         The TMDb id of the tv series.
     * @param seasonNumber     The season number of the tv series.
     * @param episodeNumber    The episode number of the tv series.
     * @param language         nullable - The language to query the results in. Default: en-US.
     * @param appendToResponse nullable - additional namespaces to append to the result (20 max).
     * @return The tv episode details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvEpisodeDb getDetails(int seriesId, int seasonNumber, int episodeNumber, String language,
                                  TvEpisodesAppendToResponse... appendToResponse) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber)
            .addLanguage(language)
            .addAppendToResponses(appendToResponse);
        return mapJsonResult(apiUrl, TvEpisodeDb.class);
    }

    /**
     * <p>Get the rating, watchlist and favourite status.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-account-states">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the tv series.
     * @param seasonNumber   The season number of the tv series.
     * @param episodeNumber  The episode number of the tv series.
     * @param sessionId      nullable - The session id of the user.
     * @param guestSessionId nullable - The guest session id of the user.
     * @return The account states.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AccountStates getAccountStates(int seriesId, int seasonNumber, int episodeNumber, String sessionId, String guestSessionId)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "account_states")
            .addQueryParam("session_id", sessionId)
            .addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, AccountStates.class);
    }

    /**
     * <p>Get the recent changes for a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-changes-by-id">documentation</a> for more info.</p>
     *
     * @param episodeId The TMDb id of the tv episode.
     * @return The change results.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangeResults getChanges(int episodeId)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_TV_EPISODE, episodeId, "changes");
        return mapJsonResult(apiUrl, ChangeResults.class);
    }

    /**
     * <p>Get the credits for a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-credits">documentation</a> for more info.</p>
     *
     * @param seriesId      The TMDb id of the tv series.
     * @param seasonNumber  The season number of the tv series.
     * @param episodeNumber The episode number of the tv series.
     * @param language      nullable - The language to query the results in. Default: en-US.
     * @return The credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public EpisodeCredits getCredits(int seriesId, int seasonNumber, int episodeNumber, String language)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "credits")
            .addLanguage(language);
        return mapJsonResult(apiUrl, EpisodeCredits.class);
    }

    /**
     * <p>Get a list of external IDs that have been added to a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-external-ids">documentation</a> for more info.</p>
     *
     * @param seriesId      The TMDb id of the tv series.
     * @param seasonNumber  The season number of the tv series.
     * @param episodeNumber The episode number of the tv series.
     * @return The external IDs.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ExternalIds getExternalIds(int seriesId, int seasonNumber, int episodeNumber)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "external_ids");
        return mapJsonResult(apiUrl, ExternalIds.class);
    }

    /**
     * <p>Get the images that belong to a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-images">documentation</a> for more info.</p>
     *
     * @param seriesId             The TMDb id of the tv series.
     * @param seasonNumber         The season number of the tv series.
     * @param episodeNumber        The episode number of the tv series.
     * @param language             nullable - The language to query the results in. Default: en-US.
     * @param includeImageLanguage nullable - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Images getImages(int seriesId, int seasonNumber, int episodeNumber, String language, String... includeImageLanguage)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "images")
            .addLanguage(language)
            .addQueryParamCommandSeparated("include_image_language", includeImageLanguage);
        return mapJsonResult(apiUrl, Images.class);
    }

    /**
     * <p>Get the translations that have been added to a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-translations">documentation</a> for more info.</p>
     *
     * @param seriesId      The TMDb id of the tv series.
     * @param seasonNumber  The season number of the tv series.
     * @param episodeNumber The episode number of the tv series.
     * @return The translations.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Translations getTranslations(int seriesId, int seasonNumber, int episodeNumber)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "translations");
        return mapJsonResult(apiUrl, Translations.class);
    }

    /**
     * <p>Get the videos that belong to a TV episode.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-videos">documentation</a> for more info.</p>
     *
     * @param seriesId             The TMDb id of the tv series.
     * @param seasonNumber         The season number of the tv series.
     * @param episodeNumber        The episode number of the tv series.
     * @param language             nullable - The language to query the results in. Default: en-US.
     * @param includeVideoLanguage nullable - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The videos.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public VideoResults getVideos(int seriesId, int seasonNumber, int episodeNumber, String language, String... includeVideoLanguage)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "videos")
            .addLanguage(language)
            .addQueryParamCommandSeparated("include_video_language", includeVideoLanguage);
        return mapJsonResult(apiUrl, VideoResults.class);
    }

    /**
     * <p>Rate a TV episode and save it to your rated list.</p>
     * <p>Note: if no <code>guestSessionId</code> or <code>sessionId</code> are provided, the method will add the rating to the API key
     * holder's account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-add-rating">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the tv series.
     * @param seasonNumber   The season number of the tv series.
     * @param episodeNumber  The episode number of the tv series.
     * @param guestSessionId nullable - The guest session id of the user.
     * @param sessionId      nullable - The session id of the user.
     * @param rating         The rating of the tv episode. Must be: 0 &lt; rating &le; 10.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus addRating(int seriesId, int seasonNumber, int episodeNumber, String guestSessionId, String sessionId,
                                    double rating) throws TmdbException {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be: 0 < rating <= 10");
        }

        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "rating")
            .addQueryParam("session_id", sessionId)
            .addQueryParam("guest_session_id", guestSessionId);

        HashMap<String, Object> body = new HashMap<>();
        body.put("value", rating);

        String jsonBody = JsonUtil.toJson(body);
        return mapJsonResult(apiUrl, jsonBody, RequestType.POST, ResponseStatus.class);
    }

    /**
     * <p>Delete your rating on a TV episode.</p>
     * <p>Note: if no <code>guestSessionId</code> or <code>sessionId</code> are provided, the method will delete the rating for the API key
     * holder's account.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-delete-rating">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the tv series.
     * @param seasonNumber   The season number of the tv series.
     * @param episodeNumber  The episode number of the tv series.
     * @param guestSessionId nullable - The guest session id of the user.
     * @param sessionId      nullable - The session id of the user.
     * @return The response status.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ResponseStatus deleteRating(int seriesId, int seasonNumber, int episodeNumber, String guestSessionId, String sessionId)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(
            TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, "rating")
            .addQueryParam("session_id", sessionId)
            .addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, null, RequestType.DELETE, ResponseStatus.class);
    }
}
