package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.tv.core.Translations;
import info.movito.themoviedbapi.model.tv.core.credits.AggregateCredits;
import info.movito.themoviedbapi.model.tv.core.credits.Credits;
import info.movito.themoviedbapi.model.tv.season.AccountStateResults;
import info.movito.themoviedbapi.model.tv.season.ChangeResults;
import info.movito.themoviedbapi.model.tv.season.ExternalIds;
import info.movito.themoviedbapi.model.tv.season.Images;
import info.movito.themoviedbapi.model.tv.season.TvSeasonDb;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.TvSeasonsAppendToResponse;

import static info.movito.themoviedbapi.TmdbTvSeries.TMDB_METHOD_TV;

/**
 * The movie database api for tv seasons. See the
 * <a href="https://developer.themoviedb.org/reference/tv-season-details">documentation</a> for more info.
 */
public class TmdbTvSeasons extends AbstractTmdbApi {
    public static final String TMDB_METHOD_TV_SEASON = "season";

    /**
     * Create a new TmdbTvSeasons instance to call the tv seasons TMDb API methods.
     */
    TmdbTvSeasons(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Query the details of the TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-details">documentation</a> for more info.</p>
     *
     * @param seriesId         The TMDb id of the TV series.
     * @param seasonNumber     The season number.
     * @param language         optional - The language to query the results in. Default: en-US.
     * @param appendToResponse optional - additional namespaces to append to the result (20 max).
     * @return The TV season details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvSeasonDb getDetails(int seriesId, int seasonNumber, String language, TvSeasonsAppendToResponse... appendToResponse)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber);
        apiUrl.addLanguage(language);
        apiUrl.addAppendToResponses(appendToResponse);
        return mapJsonResult(apiUrl, TvSeasonDb.class);
    }

    /**
     * <p>Get the rating, watchlist and favourite status.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-account-states">documentation</a> for more info.</p>
     *
     * @param seriesId       The TMDb id of the TV series.
     * @param seasonNumber   The season number.
     * @param sessionId      optional - The session id of the user.
     * @param guestSessionId optional - The guest session id of the user.
     * @return The account states.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AccountStateResults getAccountStates(int seriesId, int seasonNumber, String sessionId, String guestSessionId)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "account_states");
        apiUrl.addQueryParam("session_id", sessionId);
        apiUrl.addQueryParam("guest_session_id", guestSessionId);
        return mapJsonResult(apiUrl, AccountStateResults.class);
    }

    /**
     * <p>Get the aggregate credits (cast and crew) that have been added to a TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-aggregate-credits">documentation</a> for more info.</p>
     *
     * @param seriesId     The TMDb id of the TV series.
     * @param seasonNumber The season number.
     * @param language     optional - The language to query the results in. Default: en-US.
     * @return The aggregate credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AggregateCredits getAggregateCredits(int seriesId, int seasonNumber, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "aggregate_credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, AggregateCredits.class);
    }

    /**
     * <p>Get the recent changes for a TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-changes-by-id">documentation</a> for more info.</p>
     *
     * @param seasonId  The TMDb id of the TV season.
     * @param startDate optional - The start date, in format: YYYY-MM-DD.
     * @param endDate   optional - The end date, in format: YYYY-MM-DD.
     * @param page      optional - The page to query. Default: 1.
     * @return The changes.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ChangeResults getChanges(int seasonId, String startDate, String endDate, int page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_TV_SEASON, seasonId, "changes");
        apiUrl.addQueryParam("start_date", startDate);
        apiUrl.addQueryParam("end_date", endDate);
        apiUrl.addPage(page);
        return mapJsonResult(apiUrl, ChangeResults.class);
    }

    /**
     * <p>Get the latest tv season credits.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-credits">documentation</a> for more info.</p>
     *
     * @param seriesId     The TMDb id of the TV series.
     * @param seasonNumber The season number.
     * @param language     optional - The language to query the results in. Default: en-US.
     * @return The credits.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Credits getCredits(int seriesId, int seasonNumber, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "credits");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, Credits.class);
    }

    /**
     * <p>Get a list of external IDs that have been added to a TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-external-ids">documentation</a> for more info.</p>
     *
     * @param seriesId     The TMDb id of the TV series.
     * @param seasonNumber The season number.
     * @return The external IDs.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ExternalIds getExternalIds(int seriesId, int seasonNumber) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "external_ids");
        return mapJsonResult(apiUrl, ExternalIds.class);
    }

    /**
     * <p>Get the images that belong to a TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-images">documentation</a> for more info.</p>
     *
     * @param seriesId             The TMDb id of the TV series.
     * @param seasonNumber         The season number.
     * @param language             optional - The language to query the results in. Default: en-US.
     * @param includeImageLanguage optional - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Images getImages(int seriesId, int seasonNumber, String language, String... includeImageLanguage) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "images");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParamCommandSeparated("include_image_language", includeImageLanguage);
        return mapJsonResult(apiUrl, Images.class);
    }

    /**
     * <p>Get the translations for a TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-translations">documentation</a> for more info.</p>
     *
     * @param seriesId     The TMDb id of the TV series.
     * @param seasonNumber The season number.
     * @return The translations.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Translations getTranslations(int seriesId, int seasonNumber) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "translations");
        return mapJsonResult(apiUrl, Translations.class);
    }

    /**
     * <p>Get the videos that belong to a TV season.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-videos">documentation</a> for more info.</p>
     *
     * @param seriesId             The TMDb id of the TV series.
     * @param seasonNumber         The season number.
     * @param language             optional - The language to query the results in. Default: en-US.
     * @param includeVideoLanguage optional - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The videos.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public VideoResults getVideos(int seriesId, int seasonNumber, String language, String... includeVideoLanguage) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "videos");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParamCommandSeparated("include_video_language", includeVideoLanguage);
        return mapJsonResult(apiUrl, VideoResults.class);
    }

    /**
     * <p>Get the list of streaming providers we have for a TV season.</p>
     * <p>In order to use this data you must attribute the source of the data as JustWatch. If TMDb find any usage not complying with these
     * terms TMDb will revoke access to the API.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-season-watch-providers">documentation</a> for more info.</p>
     *
     * @param seriesId     The TMDb id of the TV series.
     * @param seasonNumber The season number.
     * @param language     optional - The language to query the results in. Default: en-US.
     * @return The watch providers.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ProviderResults getWatchProviders(int seriesId, int seasonNumber, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, "watch/providers");
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, ProviderResults.class);
    }
}
