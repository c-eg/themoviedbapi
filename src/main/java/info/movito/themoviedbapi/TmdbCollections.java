package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

import java.util.List;

import static info.movito.themoviedbapi.model.ArtworkType.BACKDROP;
import static info.movito.themoviedbapi.model.ArtworkType.POSTER;

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
     * This method is used to retrieve all of the basic information about a movie collection.
     *
     * You can get the ID needed for this method by making a getMovieInfo request for the belongs_to_collection.
     */
    public CollectionInfo getCollectionInfo(int collectionId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, CollectionInfo.class);
    }

    /**
     * Get all of the images for a particular collection by collection id.
     */
    public List<Artwork> getCollectionImages(int collectionId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId, "images");

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, MovieImages.class).getAll(POSTER, BACKDROP);
    }
}
