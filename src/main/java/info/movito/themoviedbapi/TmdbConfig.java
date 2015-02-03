package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.config.ConfigResults;
import info.movito.themoviedbapi.model.config.TmdbTimezones;
import info.movito.themoviedbapi.tools.ApiUrl;


class TmdbConfig extends AbstractTmdbApi {

    public static final String TMDB_METHOD_CONFIGURATION = "configuration";
    public static final String TMDB_METHOD_TIMEZONESLIST = "timezones/list";


    TmdbConfig(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public ConfigResults getConfig() {
        return mapJsonResult(new ApiUrl(TMDB_METHOD_CONFIGURATION), ConfigResults.class);
    }

    public TmdbTimezones getTimezones() {
        return mapJsonResult(new ApiUrl(TMDB_METHOD_TIMEZONESLIST), TmdbTimezones.class);
    }
}
