package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.tools.ApiUrl;

/**
 * The movie database api for find. See the
 * <a href="https://developer.themoviedb.org/reference/find-by-id">documentation</a> for more info.
 */
public class TmdbFind extends AbstractTmdbApi {
    public static final String TMDB_METHOD_FIND = "find";

    /**
     * Create a new TmdbFind instance to call the find related TMDb API methods.
     */
    TmdbFind(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Supported query ids are imdb, people, freebase, series. For details see     http://docs.themoviedb.apiary.io/#find
     */
    public FindResults find(String id, ExternalSource externalSource, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_FIND, id);

        apiUrl.addParam("external_source", externalSource.toString());
        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, FindResults.class);
    }

    /**
     * Supported query ids.
     */
    public enum ExternalSource {
        imdb_id,
        freebase_mid,
        freebase_id,
        tvrage_id,
        tvdb_id
    }
}
