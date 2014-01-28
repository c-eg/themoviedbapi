package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;


public class TmdbFind extends AbstractApiElement {

    public static final String TMDB_METHOD_FIND = "find";


    public enum ExternalSource {imdb_id, freebase_mid, freebase_id, tvrage_id, tvdb_id}


    TmdbFind(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * Supported query ids are imdb, people, freebase, series. For details see     http://docs.themoviedb.apiary.io/#find
     */
    public FindResults find(String id, ExternalSource externalSource, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_FIND, id);

        apiUrl.addParam("external_source", externalSource.toString());

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        return mapJsonResult(apiUrl, FindResults.class);
    }
}
