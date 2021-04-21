package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;

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


    public static class Results extends IdElement {

        @JsonProperty("results")
        private List<Video> videos;


        public List<Video> getVideos() {
            return videos;
        }
    }


    public String getSite() {
        return site;
    }


    public Integer getSize() {
        return size;
    }


    public String getKey() {
        return key;
    }


    public String getType() {
        return type;
    }


    public void setSite(String site) {
        this.site = site;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public void setSize(Integer size) {
        this.size = size;
    }


    public void setType(String type) {
        this.type = type;
    }
}
