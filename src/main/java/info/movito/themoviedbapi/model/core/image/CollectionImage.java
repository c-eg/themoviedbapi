package info.movito.themoviedbapi.model.core.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionImage extends IdElement {
    @JsonProperty("backdrops")
    private List<Artwork> backdrops;

    @JsonProperty("posters")
    private List<Artwork> posters;

    @JsonProperty("profiles")
    private List<Artwork> profiles;

    // needed for episode backdrops
    @JsonProperty("stills")
    private List<Artwork> stills;

    /**
     * Convenience wrapper to return a list of all the artwork with their types.
     */
    public List<Artwork> getAll(ArtworkType... artworkTypes) {
        List<Artwork> artwork = new ArrayList<>();
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
     * Update the artwork type for the artwork list.
     */
    private void updateArtworkType(List<Artwork> artworkList, ArtworkType type) {
        for (Artwork artwork : artworkList) {
            artwork.setArtworkType(type);
        }
    }
}
