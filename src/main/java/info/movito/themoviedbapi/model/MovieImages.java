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

    // needed for episode backdrops
    @JsonProperty("stills")
    private List<Artwork> stills;


    public List<Artwork> getBackdrops() {
        return backdrops;
    }


    public List<Artwork> getPosters() {
        return posters;
    }


    public List<Artwork> getProfiles() {
        return profiles;
    }


    public List<Artwork> getStills() {
        return stills;
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


    public void setStills(List<Artwork> stills) {
        this.stills = stills;
    }


    /**
     * Convenience wrapper to return a list of all the artwork with their types.
     */
    public List<Artwork> getAll(ArtworkType... artworkTypes) {
        List<Artwork> artwork = new ArrayList<Artwork>();
        List<ArtworkType> types = Arrays.asList(artworkTypes.length > 0 ? artworkTypes : ArtworkType.values());


        // Add all the posters to the list
        if (types.contains(ArtworkType.POSTER) && posters != null) {
            updateArtworkType(posters, ArtworkType.POSTER);
            artwork.addAll(posters);
        }

        // Add all the backdrops to the list
        if (types.contains(ArtworkType.BACKDROP) && backdrops != null) {
            updateArtworkType(backdrops, ArtworkType.BACKDROP);
            artwork.addAll(backdrops);
        }

        // Add all the backdrops to the list
        if (types.contains(ArtworkType.PROFILE) && profiles != null) {
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
