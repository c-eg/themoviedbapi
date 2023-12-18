package info.movito.themoviedbapi.api;

import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for find. See the
 * <a href="https://developer.themoviedb.org/reference/find-by-id">documentation</a> for more info.
 */
public class TmdbFind extends AbstractTmdbApi {
    private static final String TMDB_METHOD_FIND = "find";

    /**
     * Create a new TmdbFind instance to call the find related TMDb API methods.
     */
    TmdbFind(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Supported query ids are imdb, people, freebase, series. For details see     http://docs.themoviedb.apiary.io/#find
     */
    public FindResults find(String id, ExternalSource externalSource, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_FIND, id);

        apiEndpoint.addPathParam("external_source", externalSource.toString());
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, FindResults.class);
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
