package info.movito.themoviedbapi.tools.model.time;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Supported external sources.
 */
@AllArgsConstructor
@Getter
public enum ExternalSource {
    IMDB_ID("imdb_id"),
    FACEBOOK_ID("facebook_id"),
    INSTAGRAM_ID("instagram_id"),
    TVDB_ID("tvdb_id"),
    TIKTOK_ID("tiktok_id"),
    TWITTER_ID("twitter_id"),
    WIKIDATA_ID("wikidata_id"),
    YOUTUBE_ID("youtube_id");

    private final String value;
}
