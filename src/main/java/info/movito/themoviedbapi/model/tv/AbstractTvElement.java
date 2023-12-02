package info.movito.themoviedbapi.model.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.ExternalIds;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import info.movito.themoviedbapi.model.core.TvKeywords;
import info.movito.themoviedbapi.model.keywords.Keyword;

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
