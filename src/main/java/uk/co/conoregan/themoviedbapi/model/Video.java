package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import uk.co.conoregan.themoviedbapi.model.core.IdElement;
import uk.co.conoregan.themoviedbapi.model.core.NamedStringIdElement;

import java.util.List;

@JsonRootName("video")
public class Video extends NamedStringIdElement {
    @JsonProperty("site")
    private String site;

    @JsonProperty("key")
    private String key;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("type")
    private String type;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Results extends IdElement {

        @JsonProperty("results")
        private List<Video> videos;

        public List<Video> getVideos() {
            return videos;
        }
    }
}
