package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.collections.Translation;
import info.movito.themoviedbapi.model.collections.Translations;
import info.movito.themoviedbapi.model.core.image.CollectionImages;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

import java.util.List;

/**
 * The movie database api for collections. See the
 * <a href="https://developer.themoviedb.org/reference/collection-details">documentation</a> for more info.
 */
public class TmdbCollections extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_COLLECTION = "collection";

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
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId);
        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, CollectionInfo.class);
    }

    /**
     * <p>Get the images that belong to a collection.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/collection-images">documentation</a> for more info.</p>
     *
     * @param collectionId The collection id.
     * @param language optional - The language.
     * @param includeImageLanguage optional - Specify a comma separated list of ISO-639-1 values to query, for example: en,it
     * @return The images.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Artwork> getImages(Integer collectionId, String language, String... includeImageLanguage) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId, "images");
        apiUrl.addLanguage(language);
        apiUrl.addQueryParamCommandSeparated("include_image_language", includeImageLanguage);
        return mapJsonResult(apiUrl, CollectionImages.class).getAll(ArtworkType.POSTER, ArtworkType.BACKDROP);
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
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId, "translations");
        return mapJsonResult(apiUrl, Translations.class).getTranslations();
    }
}
