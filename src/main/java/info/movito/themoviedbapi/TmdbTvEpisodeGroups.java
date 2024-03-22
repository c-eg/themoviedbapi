package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.tv.episodegroups.TvEpisodeGroups;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for tv episode groups. See the
 * <a href="https://developer.themoviedb.org/reference/tv-episode-group-details">documentation</a> for more info.
 */
public class TmdbTvEpisodeGroups extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_TV_EPISODE_GROUPS = "tv/episode_group";

    /**
     * Create a new TmdbTvEpisodeGroups instance to call the tv episode groups TMDb API methods.
     */
    TmdbTvEpisodeGroups(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the details of a TV episode group.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/tv-episode-group-details">documentation</a> for more info.</p>
     *
     * @param tvEpisodeGroupId The id of the tv episode group.
     * @return The tv episode group details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public TvEpisodeGroups getDetails(String tvEpisodeGroupId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV_EPISODE_GROUPS, tvEpisodeGroupId);
        return mapJsonResult(apiUrl, TvEpisodeGroups.class);
    }
}
