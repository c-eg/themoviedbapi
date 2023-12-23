package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.Image;
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
     * <p>Get collection details by ID.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/collection-details">documentation</a> for more info.</p>
     *
     * @param collectionId The collection id.
     * @param language optional - The language.
     * @return The collection info.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public CollectionInfo getDetails(Integer collectionId, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COLLECTION, collectionId);
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, CollectionInfo.class);
    }

    /**
     * <p>Get the images that belong to a collection.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/collection-images">documentation</a> for more info.</p>
     *
     * @param collectionId The collection id.
     * @param language optional - The language.
     * @param includeImageLanguage optional - Specify a comma separated list of ISO-639-1 values to query, for example: en,null
     * @return The images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Artwork> getImages(Integer collectionId, String language, String... includeImageLanguage) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COLLECTION, collectionId, "images");

        apiEndpoint.addLanguage(language);
        apiEndpoint.addQueryParamCommandSeparated("include_image_language", includeImageLanguage);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Image.class).getAll(ArtworkType.POSTER, ArtworkType.BACKDROP);
    }

    /**
     * <p>Get all translations for a collection.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/collection-translations">documentation</a> for more info.</p>
     *
     * @param collectionId The collection id.
     * @return The translations.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Translation> getTranslations(Integer collectionId) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_COLLECTION, collectionId, "translations");

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Translations.class).getTranslations();
    }
}
