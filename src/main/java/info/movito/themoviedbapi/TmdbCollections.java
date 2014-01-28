package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.model.CollectionInfo;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class TmdbCollections extends AbstractApiElement {

    public static final String TMDB_METHOD_COLLECTION = "collection";


    TmdbCollections(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * This method is used to retrieve all of the basic information about a movie collection.
     * <p/>
     * You can get the ID needed for this method by making a getMovieInfo request for the belongs_to_collection.
     *
     * @param collectionId
     * @param language
     */
    public CollectionInfo getCollectionInfo(int collectionId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        return mapJsonResult(apiUrl, CollectionInfo.class);
    }


    /**
     * Get all of the images for a particular collection by collection id.
     *
     * @param collectionId
     * @param language
     */
    public List<Artwork> getCollectionImages(int collectionId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_COLLECTION, collectionId, "images");

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        return mapJsonResult(apiUrl, MovieImages.class).getAll(ArtworkType.POSTER, ArtworkType.BACKDROP);
    }
}
