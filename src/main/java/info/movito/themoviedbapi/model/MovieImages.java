
package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MovieImages extends IdElement {

    @JsonProperty("backdrops")
    private List<Artwork> backdrops;
    @JsonProperty("posters")
    private List<Artwork> posters;
    @JsonProperty("profiles")
    private List<Artwork> profiles;


    public List<Artwork> getBackdrops() {
        return backdrops;
    }


    public List<Artwork> getPosters() {
        return posters;
    }


    public List<Artwork> getProfiles() {
        return profiles;
    }


    public void setBackdrops(List<Artwork> backdrops) {
        this.backdrops = backdrops;
    }


    public void setPosters(List<Artwork> posters) {
        this.posters = posters;
    }


    public void setProfiles(List<Artwork> profiles) {
        this.profiles = profiles;
    }


    /**
     * Convenience wrapper to return a list of all the artwork with their types.
     */
    public List<Artwork> getAll(ArtworkType... artworkList) {
        List<Artwork> artwork = new ArrayList<Artwork>();
        List<ArtworkType> types;

        if (artworkList.length > 0) {
            types = new ArrayList<ArtworkType>(Arrays.asList(artworkList));
        } else {
            types = new ArrayList<ArtworkType>(Arrays.asList(ArtworkType.values()));
        }

        // Add all the posters to the list
        if (types.contains(ArtworkType.POSTER)) {
            updateArtworkType(posters, ArtworkType.POSTER);
            artwork.addAll(posters);
        }

        // Add all the backdrops to the list
        if (types.contains(ArtworkType.BACKDROP)) {
            updateArtworkType(backdrops, ArtworkType.BACKDROP);
            artwork.addAll(backdrops);
        }

        // Add all the backdrops to the list
        if (types.contains(ArtworkType.PROFILE)) {
            updateArtworkType(profiles, ArtworkType.PROFILE);
            artwork.addAll(profiles);
        }

        return artwork;
    }


    /**
     * Update the artwork type for the artwork list
     */
    private void updateArtworkType(List<Artwork> artworkList, ArtworkType type) {
        for (Artwork artwork : artworkList) {
            artwork.setArtworkType(type);
        }
    }
}
