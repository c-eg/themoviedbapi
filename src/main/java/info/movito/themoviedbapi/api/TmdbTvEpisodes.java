package info.movito.themoviedbapi.api;

import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.api.TmdbTV.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.api.TmdbTvEpisodes.EpisodeMethod.credits;
import static info.movito.themoviedbapi.api.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;
import static info.movito.themoviedbapi.util.Utils.asStringArray;

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
     * Gets the details for a tv episode.
     */
    public TvEpisode getEpisode(int seriesId, int seasonNumber, int episodeNumber, String language, EpisodeMethod... appendToResponse)
        throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, seriesId,
            TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber);

        apiEndpoint.addLanguage(language);
        apiEndpoint.appendToResponse(asStringArray(appendToResponse));

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvEpisode.class);
    }

    /**
     * Gets the credits for a tv episode.
     */
    public Credits getCredits(int seriesId, int seasonNumber, int episodeNumber, String language) throws TmdbException {
        ApiEndpoint apiEndpoint =
            new ApiEndpoint(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, credits);

        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Credits.class);
    }

    /**
     * Base method shared by all tv apis.
     */
    public enum EpisodeMethod {
        credits, external_ids, images, videos
        // specific method for episodes
        // ...tbd
    }
}
