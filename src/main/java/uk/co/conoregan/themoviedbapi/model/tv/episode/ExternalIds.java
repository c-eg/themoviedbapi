package uk.co.conoregan.themoviedbapi.model.tv.episode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalIds extends IdElement {
    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("freebase_id")
    private String freebaseId;

    @JsonProperty("freebase_mid")
    private String freebaseMid;

    @JsonProperty("tvdb_id")
    private String tvdbId;

    @JsonProperty("tvrage_id")
    private String tvrageId;

    @JsonProperty("wikidata_id")
    private String wikidataId;
}
