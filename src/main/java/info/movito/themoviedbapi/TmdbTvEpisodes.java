package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.TmdbTV.TMDB_METHOD_TV;
import static info.movito.themoviedbapi.TmdbTvEpisodes.EpisodeMethod.credits;
import static info.movito.themoviedbapi.TmdbTvSeasons.TMDB_METHOD_TV_SEASON;
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
    public TvEpisode getEpisode(int seriesId, int seasonNumber, int episodeNumber, String language,
                                EpisodeMethod... appendToResponse) throws TmdbException {
        ApiUrl apiUrl =
            new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber);

        apiUrl.addLanguage(language);

        apiUrl.appendToResponse(asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, TvEpisode.class);
    }

    /**
     * Gets the credits for a tv episode.
     */
    public Credits getCredits(int seriesId, int seasonNumber, int episodeNumber, String language) throws TmdbException {
        ApiUrl apiUrl =
            new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE,
                episodeNumber, credits);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, Credits.class);
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
