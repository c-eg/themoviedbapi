package uk.co.conoregan.themoviedbapi.model.tv.episode;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.tv.core.credits.Credits;

@Data
@EqualsAndHashCode(callSuper = true)
public class EpisodeCredits extends Credits {
    @JsonProperty("guest_stars")
    private List<GuestStar> guestStars = new ArrayList<>();
}
