package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

import static info.movito.themoviedbapi.TmdbTV.TMDB_METHOD_TV;

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
     * Gets the details for a tv season.
     */
    public TvSeason getSeason(int seriesId, int seasonNumber, String language, SeasonMethod... appendToResponse)
        throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber);

        apiUrl.addLanguage(language);
        //apiUrl.addAppendToResponses(asStringArray(appendToResponse)); todo fix me

        return mapJsonResult(apiUrl, TvSeason.class);
    }

    /**
     * Base method shared by all tv apis.
     */
    public enum SeasonMethod {
        credits, external_ids, images, videos
        // specific method for episodes
        // ...tbd
    }
}
