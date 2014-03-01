package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;


public class TmdbTvEpisodes extends AbstractApiElement {


    public static final String TMDB_METHOD_TV_EPISODE = "episode";


    public static enum EpisodeMethod {credits, external_ids, images}


    TmdbTvEpisodes(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public TvEpisode getEpisode(int seriesId, int seasonNumber, int episodeNumber, String language, EpisodeMethod... appendToResponse) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, seriesId, TmdbTvSeasons.TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        apiUrl.appendToResponse(Utils.asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, TvEpisode.class);
    }


    public Credits getCredits(int seriesId, int seasonNumber, int episodeNumber, String language) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, seriesId, TmdbTvSeasons.TMDB_METHOD_TV_SEASON, seasonNumber, TMDB_METHOD_TV_EPISODE, episodeNumber, TmdbTV.TMDB_METHOD_TV_CREDITS);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        return mapJsonResult(apiUrl, Credits.class);
    }
}
