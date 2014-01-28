package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;


public class Artwork extends AbstractJsonMapping {


    @JsonProperty("iso_639_1")
    private String language;

    @JsonProperty("file_path")
    private String filePath;


    @JsonProperty("aspect_ratio")
    private float aspectRatio;
    @JsonProperty("height")
    private int height;
    @JsonProperty("width")
    private int width;

    @JsonProperty("vote_average")
    private float voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("flag")
    private String flag;

    private ArtworkType artworkType = ArtworkType.POSTER;


    public ArtworkType getArtworkType() {
        return artworkType;
    }


    public float getAspectRatio() {
        return aspectRatio;
    }


    public String getFilePath() {
        return filePath;
    }


    public int getHeight() {
        return height;
    }


    public String getLanguage() {
        return language;
    }


    public int getWidth() {
        return width;
    }


    public float getVoteAverage() {
        return voteAverage;
    }


    public int getVoteCount() {
        return voteCount;
    }


    public String getFlag() {
        return flag;
    }


    public void setArtworkType(ArtworkType artworkType) {
        this.artworkType = artworkType;
    }


    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }


    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }


    public void setFlag(String flag) {
        this.flag = flag;
    }
}
