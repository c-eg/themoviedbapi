package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.IdElement;


public class ExternalIds extends IdElement {

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("freebase_id")
    private String freeBaseId;

    @JsonProperty("freebase_mid")
    private String freebaseMid;

    @JsonProperty("tvdb_id")
    private String tvdbId;

    @JsonProperty("tvrage_id")
    private String tvrageId;


    public String getImdbId() {
        return imdbId;
    }


    public String getFreeBaseId() {
        return freeBaseId;
    }


    public String getFreebaseMid() {
        return freebaseMid;
    }


    public String getTvdbId() {
        return tvdbId;
    }


    public String getTvrageId() {
        return tvrageId;
    }


    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }


    public void setFreeBaseId(String freeBaseId) {
        this.freeBaseId = freeBaseId;
    }


    public void setFreebaseMid(String freebaseMid) {
        this.freebaseMid = freebaseMid;
    }


    public void setTvdbId(String tvdbId) {
        this.tvdbId = tvdbId;
    }


    public void setTvrageId(String tvrageId) {
        this.tvrageId = tvrageId;
    }
}
