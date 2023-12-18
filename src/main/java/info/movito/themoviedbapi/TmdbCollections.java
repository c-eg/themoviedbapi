package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.Translation;
import info.movito.themoviedbapi.model.Translations;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.tools.TmdbException;

import java.util.List;

/**
 * The movie database api for collections. See the
 * <a href="https://developer.themoviedb.org/reference/collection-details">documentation</a> for more info.
 */
public class TmdbCollections extends AbstractTmdbApi {
    public static final String TMDB_METHOD_COLLECTION = "collection";

    /**
     * Create a new TmdbCollections instance to call the collections related TMDb API methods.
     */
    TmdbCollections(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * This method is used to retrieve all the basic information about a movie collection.
     *
     * You can get the ID needed for this method by making a getMovieInfo request for the belongs_to_collection.
     */
    public CollectionInfo getDetails(int collectionId, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COLLECTION, collectionId);
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CollectionInfo.class);
    }

    /**
     * Get all the images for a particular collection by collection id.
     */
    public List<Artwork> getImages(int collectionId, String language, Boolean includeImageLanguage) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COLLECTION, collectionId, "images");

        apiEndpoint.addLanguage(language);
        apiEndpoint.addQueryParam("include_image_language", includeImageLanguage);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieImages.class).getAll(ArtworkType.POSTER, ArtworkType.BACKDROP);
    }

    /**
     * Get all translations for a collection.
     */
    public List<Translation> getTranslations(int collectionId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COLLECTION, collectionId, "translations");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Translations.class).getTranslations();
    }
}
