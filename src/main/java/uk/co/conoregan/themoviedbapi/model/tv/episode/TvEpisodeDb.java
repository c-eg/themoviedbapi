package uk.co.conoregan.themoviedbapi.model.tv.episode;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AccountStates;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.core.video.VideoResults;
import uk.co.conoregan.themoviedbapi.model.tv.core.Translations;
import uk.co.conoregan.themoviedbapi.model.tv.core.credits.Crew;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvEpisodeDb extends NamedIdElement {
    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("episode_number")
    private Integer episodeNumber;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("production_code")
    private String productionCode;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("still_path")
    private String stillPath;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    @JsonProperty("crew")
    private List<Crew> crew = new ArrayList<>();

    @JsonProperty("guest_stars")
    private List<GuestStar> guestStars = new ArrayList<>();

    /* append to responses */

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("account_states")
    private AccountStates accountStates;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("credits")
    private EpisodeCredits credits;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("images")
    private Images images;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("translations")
    private Translations translations;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("videos")
    private VideoResults videos;
}
