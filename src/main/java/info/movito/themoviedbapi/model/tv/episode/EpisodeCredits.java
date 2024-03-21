package info.movito.themoviedbapi.model.tv.episode;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.tv.core.credits.Credits;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class EpisodeCredits extends Credits {
    @JsonProperty("guest_stars")
    private List<GuestStar> guestStars;
}
