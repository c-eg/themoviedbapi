package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.model.networks.AlternativeNamesResults;
import info.movito.themoviedbapi.model.networks.Network;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;

/**
 * The movie database api for networks. See the
 * <a href="https://developer.themoviedb.org/reference/network-details">documentation</a> for more info.
 */
public class TmdbNetworks extends AbstractTmdbApi {
    protected static final String TMDB_METHOD_NETWORK = "network";

    /**
     * Create a new TmdbNetworks instance to call the network related TMDb API methods.
     */
    public TmdbNetworks(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Get the details of a network.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/network-details">documentation</a> for more info.</p>
     *
     * @param networkId The network id.
     * @return The network details.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Network getDetails(int networkId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_NETWORK, networkId);
        return mapJsonResult(apiUrl, Network.class);
    }

    /**
     * <p>Get the alternative names of a network.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/details-copy">documentation</a> for more info.</p>
     *
     * @param networkId The network id.
     * @return The alternative names of the network.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public AlternativeNamesResults getAlternativeNames(int networkId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_NETWORK, networkId, "alternative_names");
        return mapJsonResult(apiUrl, AlternativeNamesResults.class);
    }

    /**
     * <p>Get the images of a network.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/alternative-names-copy">documentation</a> for more info.</p>
     *
     * @param networkId The network id.
     * @return The images of the network.
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public ImageResults getImages(int networkId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_NETWORK, networkId, "images");
        return mapJsonResult(apiUrl, ImageResults.class);
    }
}
