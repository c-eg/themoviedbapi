package uk.co.conoregan.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.conoregan.themoviedbapi.model.Credits;
import uk.co.conoregan.themoviedbapi.model.ExternalIds;
import uk.co.conoregan.themoviedbapi.model.MovieImages;
import uk.co.conoregan.themoviedbapi.model.Video;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;
import uk.co.conoregan.themoviedbapi.model.core.TvKeywords;
import uk.co.conoregan.themoviedbapi.model.keywords.Keyword;

import java.util.List;

public class AbstractTvElement extends NamedIdElement {
    // Appendable responses for all tv elements

    @JsonProperty("credits")
    private Credits credits;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @JsonProperty("images")
    private MovieImages images;

    @JsonProperty("videos")
    private Video.Results videos;

    @JsonProperty("keywords")
    private TvKeywords keywords;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits c) {
        credits = c;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds e) {
        externalIds = e;
    }

    public MovieImages getImages() {
        return images;
    }

    public void setImages(MovieImages images) {
        this.images = images;
    }

    public List<Video> getVideos() {
        return videos != null ? videos.getVideos() : null;
    }

    public void setVideos(Video.Results videos) {
        this.videos = videos;
    }

    public List<Keyword> getKeywords() {
        return keywords != null ? keywords.getKeywords() : null;
    }

    public void setKeywords(TvKeywords keywords) {
        this.keywords = keywords;
    }
}
