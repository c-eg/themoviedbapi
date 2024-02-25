package info.movito.themoviedbapi.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalIds extends IdElement {
    @JsonProperty("freebase_mid")
    private String freebaseMid;

    @JsonProperty("freebase_id")
    private String freebaseId;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("tvrage_id")
    private Integer tvrageId;

    @JsonProperty("wikidata_id")
    private String wikidataId;

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("instagram_id")
    private String instagramId;

    @JsonProperty("tiktok_id")
    private String tiktokId;

    @JsonProperty("twitter_id")
    private String twitterId;

    @JsonProperty("youtube_id")
    private String youtubeId;
}
