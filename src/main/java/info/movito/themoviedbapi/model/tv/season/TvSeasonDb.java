package info.movito.themoviedbapi.model.tv.season;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.core.watchproviders.ProviderResults;
import info.movito.themoviedbapi.model.tv.episode.TvEpisodeDb;
import info.movito.themoviedbapi.model.tv.series.credits.AggregateCredits;
import info.movito.themoviedbapi.model.tv.series.credits.Credits;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TvSeasonDb extends NamedIdElement {
    @JsonProperty("_id")
    private String underscoreId;

    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("episodes")
    private List<TvEpisodeDb> episodes;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("vote_average")
    private Double voteAverage;

    /* append to responses */

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("account_states")
    private AccountStateResults accountStates;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("aggregate_credits")
    private AggregateCredits aggregateCredits;

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("credits")
    private Credits credits;

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

    /** Can be null if not appended to the request (append to response). */
    @JsonProperty("watch/providers")
    private ProviderResults watchProviders;
}
