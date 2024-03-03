package info.movito.themoviedbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalIds extends IdElement {
    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("wikidata_id")
    private String wikidataId;

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("instagram_id")
    private String instagramId;

    @JsonProperty("twitter_id")
    private String twitterId;
}
